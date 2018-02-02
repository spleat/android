package io.github.spleat

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.home_activity.*

class HomeActivity : RxAppCompatActivity() {

    private val walletManager by lazy(walletManagerProvider)
    private val etherPizza by lazy(etherPizzaServiceProvider)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        yourAddress.text = walletManager.getWallet().address
        etherPizza.getBalance()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .bindToLifecycle(this)
                .subscribe({
                    balance.text = it.toString().dropLast(18)
                }, {
                    Log.e("kasper", it.toString(), it)
                })
        restaurantChooser.adapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, listOf("Ether Pizza", "Dominos"))
        createButton.setOnClickListener {
            MenuActivity.start(this)
        }
    }
}