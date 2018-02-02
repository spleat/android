package io.github.spleat

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.LinearLayout
import com.elpassion.android.commons.recycler.adapters.basicAdapterWithLayoutAndBinder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.menu_activity.*
import kotlinx.android.synthetic.main.menu_item_layout.view.*
import java.math.BigInteger

class MenuActivity : AppCompatActivity() {

    private val menuService: EtherPizzaService by lazy(etherPizzaServiceProvider)
    private val menuAdapter = basicAdapterWithLayoutAndBinder(
            items = emptyList<MenuItem>(),
            layout = R.layout.menu_item_layout,
            binder = { holder, item ->
                with(holder.itemView) {
                    menuItemDescription.text = item.description
                    menuItemPrice.text = item.price
                }
            })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_activity)
        menuListRecycler.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        menuService.executeRx { menuLength().sendAsync() }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMapIterable { (0..(it.toLong() - 1)).toList() }
                .doOnNext { Log.e("kasper","calling $it")}
                .flatMap { menuService.executeRx { menuItem(BigInteger.valueOf(it)).sendAsync() } }
                .map { MenuItem(it.value1.toString(), it.value2.toString(), it.value3.toString()) }
                .toList()
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
        val price: String
)