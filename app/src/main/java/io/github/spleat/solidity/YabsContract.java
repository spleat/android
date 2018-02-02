package io.github.spleat.solidity;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.1.1.
 */
public final class YabsContract extends Contract {
    private static final String BINARY = "6060604052341561000f57600080fd5b600160a060020a0333166000908152602081815260408083208380529091529020683635c9adc5dea0000090556108598061004b6000396000f3006060604052600436106100ae5763ffffffff7c01000000000000000000000000000000000000000000000000000000006000350416632db678bf81146100b35780633340ab2f146100d7578063403b2ba7146100ff578063507cd30f146101215780635b14972c146101435780635e2381031461016b5780636c951ccc146101c3578063a5633b69146101e5578063b77751ab14610207578063c23f001f14610229578063d4fac45d14610260575b600080fd5b34156100be57600080fd5b6100d5600160a060020a0360043516602435610285565b005b34156100e257600080fd5b6100d5600435600160a060020a03602435166044356064356102e4565b341561010a57600080fd5b6100d5600160a060020a0360043516602435610369565b341561012c57600080fd5b6100d5600160a060020a03600435166024356104e2565b341561014e57600080fd5b6100d5600435600160a060020a0360243516604435606435610512565b341561017657600080fd5b61018d600160a060020a0360043516602435610597565b6040518084600160a060020a0316600160a060020a03168152602001838152602001828152602001935050505060405180910390f35b34156101ce57600080fd5b61018d600160a060020a03600435166024356105cb565b34156101f057600080fd5b6100d5600160a060020a03600435166024356105ff565b341561021257600080fd5b6100d5600160a060020a0360043516602435610673565b341561023457600080fd5b61024e600160a060020a03600435811690602435166107ea565b60405190815260200160405180910390f35b341561026b57600080fd5b61024e600160a060020a0360043581169060243516610804565b600160a060020a0333811660009081526020818152604080832093861683529290522054819010156102b357fe5b600160a060020a03338116600090815260208181526040808320959093168252939093529091208054919091039055565b60606040519081016040908152600160a060020a038086168352602080840186905282840185905233909116600090815260018252828120888252909152208151815473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a03919091161781556020820151816001015560408201516002909101555050505050565b600160a060020a0382166000818152600160209081526040808320858452825280832060028101549484528383528184208480529092529091205490919010156103af57fe5b6001810154600160a060020a0333811660009081526020818152604080832086549094168352929052205410156103e257fe5b600281018054600160a060020a0385811660009081526020818152604080832083805280835281842080549690960390955594543384168352828252858320838052808352868420805490920190915560018701805488548616855295835286842080549096019095559354865490931682529290925290829020805491909103905560609051908101604090815260008083526020808401829052828401829052600160a060020a0387168252600181528282208683529052208151815473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a039190911617815560208201518160010155604082015160029091015550505050565b600160a060020a039182166000908152602081815260408083203390951683529390529190912080549091019055565b60606040519081016040908152600160a060020a038086168352602080840186905282840185905233909116600090815260028252828120888252909152208151815473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a03919091161781556020820151816001015560408201516002909101555050505050565b60026020818152600093845260408085209091529183529120805460018201549190920154600160a060020a039092169183565b60016020818152600093845260408085209091529183529120805491810154600290910154600160a060020a039092169183565b600160a060020a0333166000908152602081815260408083208380529091529020548190101561062b57fe5b600160a060020a033381166000908152602081815260408083208380528252808320805486900390559490921681528082528381208180529091529190912080549091019055565b600160a060020a03808316600081815260026020908152604080832086845282528083206001810154948452838352818420815490961684529490915290205410156106bb57fe5b6002810154600160a060020a03331660009081526020818152604080832083805290915290205410156106ea57fe5b600281018054600160a060020a0385811660009081526020818152604080832083805280835281842080549096019095559454338416835282825285832083805280835286842080549290920390915560018701805488548616855295835286842080549690960390955593548654909316825292909252908290208054909101905560609051908101604090815260008083526020808401829052828401829052600160a060020a0387168252600281528282208683529052208151815473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a039190911617815560208201518160010155604082015160029091015550505050565b600060208181529281526040808220909352908152205481565b600160a060020a03918216600090815260208181526040808320939094168252919091522054905600a165627a7a723058200a46d844514d6aae33138592ec66c99fd848d1dee9f83ded76cfd1e60dcf057d0029";

    private YabsContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    private YabsContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<TransactionReceipt> claimPromoCode(String retailer, BigInteger points) {
        Function function = new Function(
                "claimPromoCode", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(retailer), 
                new org.web3j.abi.datatypes.generated.Uint256(points)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> createBuyOffer(BigInteger id, String retailer, BigInteger points, BigInteger yabs) {
        Function function = new Function(
                "createBuyOffer", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(id), 
                new org.web3j.abi.datatypes.Address(retailer), 
                new org.web3j.abi.datatypes.generated.Uint256(points), 
                new org.web3j.abi.datatypes.generated.Uint256(yabs)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> acceptBuyOffer(String user, BigInteger id) {
        Function function = new Function(
                "acceptBuyOffer", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(user), 
                new org.web3j.abi.datatypes.generated.Uint256(id)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> addPoints(String user, BigInteger points) {
        Function function = new Function(
                "addPoints", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(user), 
                new org.web3j.abi.datatypes.generated.Uint256(points)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> createSellOffer(BigInteger id, String retailer, BigInteger points, BigInteger yabs) {
        Function function = new Function(
                "createSellOffer", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(id), 
                new org.web3j.abi.datatypes.Address(retailer), 
                new org.web3j.abi.datatypes.generated.Uint256(points), 
                new org.web3j.abi.datatypes.generated.Uint256(yabs)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Tuple3<String, BigInteger, BigInteger>> sellOffers(String param0, BigInteger param1) {
        final Function function = new Function("sellOffers", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(param0), 
                new org.web3j.abi.datatypes.generated.Uint256(param1)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple3<String, BigInteger, BigInteger>>(
                new Callable<Tuple3<String, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple3<String, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);;
                        return new Tuple3<String, BigInteger, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue());
                    }
                });
    }

    public RemoteCall<Tuple3<String, BigInteger, BigInteger>> buyOffers(String param0, BigInteger param1) {
        final Function function = new Function("buyOffers", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(param0), 
                new org.web3j.abi.datatypes.generated.Uint256(param1)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple3<String, BigInteger, BigInteger>>(
                new Callable<Tuple3<String, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple3<String, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);;
                        return new Tuple3<String, BigInteger, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue());
                    }
                });
    }

    public RemoteCall<TransactionReceipt> transferYabs(String receiver, BigInteger yabs) {
        Function function = new Function(
                "transferYabs", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(receiver), 
                new org.web3j.abi.datatypes.generated.Uint256(yabs)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> acceptSellOffer(String user, BigInteger id) {
        Function function = new Function(
                "acceptSellOffer", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(user), 
                new org.web3j.abi.datatypes.generated.Uint256(id)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> balances(String param0, String param1) {
        Function function = new Function("balances", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(param0), 
                new org.web3j.abi.datatypes.Address(param1)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> getBalance(String user, String retailer) {
        Function function = new Function("getBalance", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(user), 
                new org.web3j.abi.datatypes.Address(retailer)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public static RemoteCall<YabsContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(YabsContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<YabsContract> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(YabsContract.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static YabsContract load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new YabsContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static YabsContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new YabsContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }
}
