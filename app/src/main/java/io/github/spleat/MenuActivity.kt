package io.github.spleat

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.elpassion.android.commons.recycler.adapters.basicAdapterWithLayoutAndBinder
import kotlinx.android.synthetic.main.menu_activity.*
import kotlinx.android.synthetic.main.menu_item_layout.view.*

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_activity)
        menuListRecycler.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        menuListRecycler.adapter = basicAdapterWithLayoutAndBinder(
                items = emptyList<MenuItem>(),
                layout = R.layout.menu_item_layout,
                binder = { holder, item ->
                    with(holder.itemView) {
                        menuItemDescription.text = item.description
                        menuItemPrice.text = item.price
                    }
                })
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