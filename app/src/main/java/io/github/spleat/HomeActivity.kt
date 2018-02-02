package io.github.spleat

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.home_activity.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        restaurantChooser.adapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, listOf("Ether Pizza", "Dominos"))
        createButton.setOnClickListener {
            MenuActivity.start(this)
        }
    }
}