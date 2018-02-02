package io.github.spleat

import android.os.Bundle
import android.widget.ArrayAdapter
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import kotlinx.android.synthetic.main.home_activity.*

class HomeActivity : RxAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        restaurantChooser.adapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, listOf("Ether Pizza", "Dominos"))
        createButton.setOnClickListener {
            MenuActivity.start(this)
        }
    }
}