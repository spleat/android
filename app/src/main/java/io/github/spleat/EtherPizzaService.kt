package io.github.spleat

import io.github.spleat.solidity.EtherPizza
import io.reactivex.Observable
import org.web3j.protocol.core.DefaultBlockParameterName
import org.web3j.protocol.core.JsonRpc2_0Web3j
import org.web3j.protocol.infura.InfuraHttpService
import org.web3j.tx.Contract
import org.web3j.tx.ManagedTransaction
import java.math.BigInteger
import java.util.concurrent.Future

class EtherPizzaService(private val walletManager: WalletManager) {

    private val web3 by lazy { JsonRpc2_0Web3j(InfuraHttpService("https://rinkeby.infura.io/0ZevQ4HkUCzCVBOsYZcQ")) }

    fun <T> executeRx(f: EtherPizza.() -> Future<out T>): Observable<out T> {
        val contract = EtherPizza.load("0x89e5275771b16388e872e0ed7f1455a7683a0bc9", web3, walletManager.getWallet(), ManagedTransaction.GAS_PRICE, Contract.GAS_LIMIT)
        return Observable.fromFuture(f(contract))
    }

    fun getBalance(): Observable<BigInteger> {
        return Observable.fromFuture(web3.ethGetBalance(walletManager.getWallet().address, DefaultBlockParameterName.LATEST).sendAsync())
                .map { it.balance }
    }
}