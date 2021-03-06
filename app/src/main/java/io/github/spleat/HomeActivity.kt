package io.github.spleat

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import com.elpassion.android.view.hide
import com.elpassion.android.view.show
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.home_activity.*

class HomeActivity : RxAppCompatActivity() {

    private val walletManager by lazy(walletManagerProvider)
    private val etherPizza by lazy(etherPizzaServiceProvider)
    private val spleat by lazy(spleatServiceProvider)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        val address = walletManager.getWallet().address
        restaurantChooser.adapter = ArrayAdapter(this, R.layout.spinner_item, listOf("Bitcoin Burger", "Ether Pizza"))
        createButton.setOnClickListener {
            createButton.hide()
            progressBar.show()
            createOrder(address)
        }
    }

    private fun createOrder(address: String) {
        spleat.executeRx { openOrder(EtherPizzaService.ADDRESS, addressView.text.toString(), phoneNumber.text.toString()).sendAsync() }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .bindToLifecycle(this)
                .subscribe({
                    MenuActivity.start(this, it.logs[0].topics[1].toUint256(), address)
                }, {
                    Log.e("kasper", it.toString(), it)
                })
    }

    override fun onResume() {
        super.onResume()
        etherPizza.getBalance()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .bindToLifecycle(this)
                .subscribe({
                    balance.text = it.toEth().toPlainString() + " ETH"
                    addressView.setText("Powstańców Śląskich 7b, 53-332 Wrocław")
                }, {
                    Log.e("kasper", it.toString(), it)
                })
    }
}