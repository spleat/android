package io.github.spleat

import io.github.spleat.solidity.Spleat
import io.reactivex.Observable
import org.web3j.protocol.core.JsonRpc2_0Web3j
import org.web3j.tx.Contract
import org.web3j.tx.ManagedTransaction
import java.util.concurrent.Future

class SpleatService(private val walletManager: WalletManager,
                    private val web3: JsonRpc2_0Web3j) {

    fun <T> executeRx(f: Spleat.() -> Future<out T>): Observable<out T> {
        return Observable.fromCallable { Spleat.load("0x5c76f365acf895b84108142da0da2c7b7ee37619", web3, walletManager.getWallet(), ManagedTransaction.GAS_PRICE, Contract.GAS_LIMIT) }
                .flatMap { Observable.fromFuture(f(it)) }
    }
}