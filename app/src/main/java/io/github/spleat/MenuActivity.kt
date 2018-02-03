package io.github.spleat

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.LinearLayout
import com.elpassion.android.commons.recycler.adapters.basicAdapterWithLayoutAndBinder
import com.elpassion.android.view.hide
import com.elpassion.android.view.show
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.current_item_layout.view.*
import kotlinx.android.synthetic.main.menu_activity.*
import kotlinx.android.synthetic.main.menu_item_layout.view.*
import org.web3j.abi.datatypes.Address
import org.web3j.abi.datatypes.generated.Uint256
import org.web3j.tuples.generated.Tuple4
import java.math.BigDecimal
import java.math.BigInteger
import java.util.concurrent.TimeUnit

class MenuActivity : RxAppCompatActivity() {

    private val orderId by lazy { intent.getStringExtra(ORDER_ID_KEY) ?: intent.data.path }
    private val walletManager by lazy(walletManagerProvider)
    private val menuService: EtherPizzaService by lazy(etherPizzaServiceProvider)
    private val spleatService by lazy(spleatServiceProvider)
    private val menuAdapter = basicAdapterWithLayoutAndBinder(
            items = emptyList<MenuItem>(),
            layout = R.layout.menu_item_layout,
            binder = { holder, item ->
                with(holder.itemView) {
                    menuItemDescription.text = item.description
                    menuItemPrice.text = item.price.toEth().toPlainString().trimEnd('0') + " ETH"
                    menuItemAdd.setOnClickListener {
                        addMenuItem(item)
                    }
                }
            })

    private val myAddress by lazy { intent.getStringExtra(ADDRESS_KEY) ?: walletManager.getWallet().address }

    private val currentOrderAdapter = basicAdapterWithLayoutAndBinder(
            items = emptyList<OwnerableMenuItem>(),
            layout = R.layout.current_item_layout,
            binder = { holder, item ->
                with(holder.itemView) {
                    currentItemDescription.text = item.menuItem.description
                    if (item.owner.toString() == myAddress) {
                        setBackgroundColor(resources.getColor(R.color.yellow))
                        currentItemRemove.show()
                    } else {
                        currentItemRemove.hide()
                        setBackgroundColor(resources.getColor(R.color.background_material_light))
                    }
                    currentItemRemove.setOnClickListener {
                        removeMenuItem(item.menuItem)
                    }
                }
            })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_activity)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)
        val title = "EtherPizza - order"
        supportActionBar!!.title = title
        toolbar.title = title
        shareOrder.setOnClickListener {
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(Intent.EXTRA_TEXT, "http://sple.at/$orderId")
            sendIntent.type = "text/plain"
            startActivity(Intent.createChooser(sendIntent, "http://sple.at/$orderId"))
        }
        Log.e("kasper", "orderId: $orderId")
        currentOrderRecycler.adapter = currentOrderAdapter
        menuListRecycler.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        currentOrderRecycler.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        menuListRecycler.adapter = menuAdapter
        fetchMenuItems()
        subscribeToCurrentOrderChanges()
        closeOrder.setOnClickListener {
            closeOrderAndSubscribeToStatusChanges()
        }
    }

    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun closeOrderAndSubscribeToStatusChanges() {
        spleatService.executeRx { makeOrder(orderId.toUint256()).sendAsync() }
                .subscribeOn(Schedulers.io())
                .flatMap { Observable.interval(2, TimeUnit.SECONDS) }
                .toFlowable(BackpressureStrategy.DROP)
                .toObservable()
                .flatMap { spleatService.executeRx { restaurantOrderStatus(orderId.toUint256()).sendAsync() } }
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { progressBar.show() }
                .bindToLifecycle(this)
                .subscribe({
                    progressBar.hide()
                    closeOrder.hide()
                    statusView.show()
                    statusView.text = when (it.toInt()) {
                        0 -> "pending"
                        1 -> "preparing"
                        2 -> "delivering"
                        3 -> "delivered"
                        else -> "rejected"
                    }
                }, {
                    Log.e("kasper", it.toString(), it)
                })
    }

    private fun subscribeToCurrentOrderChanges() {
        Flowable.interval(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .onBackpressureDrop()
                .toObservable()
                .flatMap { spleatService.executeRx { orderById(orderId.toUint256()).sendAsync() } }
                .map { a: Tuple4<MutableList<BigInteger>, MutableList<String>, Boolean, String> ->
                    try {
                        val cos = a as Tuple4<MutableList<Uint256>, MutableList<Address>, Boolean, String>
                        val isOwner = cos.value4 == myAddress && cos.value1.isNotEmpty()
                        cos.value1.zip(cos.value2).map { i -> OwnerableMenuItem(i.second, menuAdapter.items.single { Uint256(it.id) == i.first }) } to isOwner
                    } catch (ex: Exception) {
                        emptyList<OwnerableMenuItem>() to false
                    }
                }
                .distinctUntilChanged()
                .observeOn(AndroidSchedulers.mainThread())
                .bindToLifecycle(this)
                .subscribe({ (it, isOwner) ->
                    if (isOwner && it.isNotEmpty()) {
                        closeOrder.show()
                    } else {
                        closeOrder.hide()
                    }
                    progressBar.hide()
                    Log.e("kasper", "ok $it")
                    currentOrderAdapter.items = it
                    currentOrderAdapter.notifyDataSetChanged()
                }, {
                    Log.e("kasper", it.toString(), it)

                })
    }

    private fun fetchMenuItems() {
        menuService.executeRx { menuLength().sendAsync() }
                .subscribeOn(Schedulers.io())
                .flatMapIterable { (0..(it.toLong() - 1)).toList() }
                .doOnNext { Log.e("kasper", "calling $it") }
                .flatMap { menuService.executeRx { menuItem(BigInteger.valueOf(it)).sendAsync() } }
                .map { MenuItem(it.value1, it.value2.toString(), it.value3) }
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    progressBar.show()
                }
                .doFinally {
                    progressBar.hide()
                }
                .bindToLifecycle(this)
                .subscribe({
                    menuAdapter.items = it
                    menuAdapter.notifyDataSetChanged()
                }, {
                    Log.e("kasper", it.toString(), it)
                })
    }

    private fun String.toUint256() = BigInteger(this.substringAfter("0x"), 16)

    private fun addMenuItem(item: MenuItem) {
        spleatService.executeRx { addItem(orderId.toUint256(), item.id, item.price).sendAsync() }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { progressBar.show() }
                .doFinally { progressBar.hide() }
                .bindToLifecycle(this@MenuActivity)
                .subscribe({
                    Log.e("kasper", "item dodany")
                }, {
                    Log.e("kasper", it.toString(), it)
                })
    }

    private fun removeMenuItem(item: MenuItem) {
        spleatService.executeRx { removeItem(orderId.toUint256(), item.id).sendAsync() }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { progressBar.show() }
                .doFinally { progressBar.hide() }
                .bindToLifecycle(this@MenuActivity)
                .subscribe({
                    Log.e("kasper", "item dodany")
                }, {
                    Log.e("kasper", it.toString(), it)
                })
    }

    companion object {
        fun start(context: Context, orderId: String, address: String) {
            context.startActivity(Intent(context, MenuActivity::class.java)
                    .putExtra(ORDER_ID_KEY, orderId)
                    .putExtra(ADDRESS_KEY, address))
        }

        private const val ORDER_ID_KEY = "orderId"
        private const val ADDRESS_KEY = "address"
    }
}

data class MenuItem(
        val id: BigInteger,
        val description: String,
        val price: BigInteger
)

data class OwnerableMenuItem(
        val owner: Address,
        val menuItem: MenuItem
)

fun BigInteger.toEth(): BigDecimal =
        toBigDecimal().setScale(6).div(BigDecimal("1e18"))
