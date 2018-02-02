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
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.menu_activity.*
import kotlinx.android.synthetic.main.menu_item_layout.view.*
import java.math.BigDecimal
import java.math.BigInteger

class MenuActivity : RxAppCompatActivity() {

    private val menuService: EtherPizzaService by lazy(etherPizzaServiceProvider)
    private val menuAdapter = basicAdapterWithLayoutAndBinder(
            items = emptyList<MenuItem>(),
            layout = R.layout.menu_item_layout,
            binder = { holder, item ->
                with(holder.itemView) {
                    menuItemDescription.text = item.description
                    menuItemPrice.text = item.price.toString()
                }
            })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_activity)
        menuListRecycler.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        currentOrderRecycler.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        menuService.executeRx { menuLength().sendAsync() }
                .subscribeOn(Schedulers.io())
                .flatMapIterable { (0..(it.toLong() - 1)).toList() }
                .doOnNext { Log.e("kasper", "calling $it") }
                .flatMap { menuService.executeRx { menuItem(BigInteger.valueOf(it)).sendAsync() } }
                .map { MenuItem(it.value1.toString(), it.value2.toString(), it.value3.toEth()) }
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
        menuListRecycler.adapter = menuAdapter
    }


    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, MenuActivity::class.java))
        }
    }
}

data class MenuItem(
        val id: String,
        val description: String,
        val price: BigDecimal
)

fun BigInteger.toEth(): BigDecimal =
        toBigDecimal(scale = 2).div(BigDecimal.valueOf(Math.pow(10.0, 18.0).toLong()))
