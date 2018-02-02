package io.github.spleat.solidity;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.EventValues;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple7;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import rx.Observable;
import rx.functions.Func1;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.2.0.
 */
public class Spleat extends Contract {
    private static final String BINARY = "6060604052341561000f57600080fd5b610be18061001e6000396000f30060606040526004361061005e5763ffffffff60e060020a6000350416631f0cbfb9811461006357806326e5f7851461007b57806375b2100a1461012e578063870fc34714610168578063a212523214610181578063a85c38ef1461018f575b600080fd5b341561006e57600080fd5b6100796004356102db565b005b341561008657600080fd5b61011c60048035600160a060020a03169060446024803590810190830135806020601f8201819004810201604051908101604052818152929190602084018383808284378201915050505050509190803590602001908201803590602001908080601f01602080910402602001604051908101604052818152929190602084018383808284375094965061050d95505050505050565b60405190815260200160405180910390f35b341561013957600080fd5b6101446004356107b1565b6040518082600481111561015457fe5b60ff16815260200191505060405180910390f35b341561017357600080fd5b61007960043560243561083c565b610079600435602435610868565b341561019a57600080fd5b6101a56004356109d0565b604051600160a060020a038881168252871660208201526080810184905282151560a082015260c0810182905260e060408201818152875460026001821615610100908102600019019092160492840183905290916060840191840190899080156102515780601f1061022657610100808354040283529160200191610251565b820191906000526020600020905b81548152906001019060200180831161023457829003601f168201915b50508381038252875460026000196101006001841615020190911604808252602090910190889080156102c55780601f1061029a576101008083540402835291602001916102c5565b820191906000526020600020905b8154815290600101906020018083116102a857829003601f168201915b5050995050505050505050505060405180910390f35b6000818152602081905260408120548190839033600160a060020a0390811691161461030657600080fd5b600084815260208190526040902060070154849060ff161561032757600080fd5b60008581526020819052604080822060018101546006820154919750600160a060020a03169263852d599792600289019160038a019160048b019190516020015260405160e060020a63ffffffff8716028152606060048201908152845460026000196101006001841615020190911604606483018190529091829160248201916044810191608490910190889080156104025780601f106103d757610100808354040283529160200191610402565b820191906000526020600020905b8154815290600101906020018083116103e557829003601f168201915b50508481038352865460026000196101006001841615020190911604808252602090910190879080156104765780601f1061044b57610100808354040283529160200191610476565b820191906000526020600020905b81548152906001019060200180831161045957829003601f168201915b505084810382528581815481526020019150805480156104b557602002820191906000526020600020905b8154815260200190600101908083116104a1575b505096505050505050506020604051808303818588803b15156104d757600080fd5b6125ee5a03f115156104e857600080fd5b50505050604051805160088601555050506007909101805460ff191660011790555050565b6000808484846040516c01000000000000000000000000600160a060020a0385160281526014810183805190602001908083835b602083106105605780518252601f199092019160209182019101610541565b6001836020036101000a038019825116818451161790925250505091909101905082805190602001908083835b602083106105ac5780518252601f19909201916020918201910161058d565b6001836020036101000a038019825116818451161790925250505091909101945060409350505050519081900390206000194301401890506101206040519081016040528033600160a060020a0316815260200186600160a060020a03168152602001858152602001848152602001600060405180591061062a5750595b90808252806020026020018201604052508152602001600060405180591061064f5750595b908082528060200260200182016040525081526000602080830182905260408084018390526060909301829052848252819052208151815473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a0391909116178155602082015160018201805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a03929092169190911790556040820151816002019080516106fb929160200190610a16565b50606082015181600301908051610716929160200190610a16565b50608082015181600401908051610731929160200190610a94565b5060a08201518160050190805161074c929160200190610ace565b5060c0820151816006015560e082015160078201805460ff191691151591909117905561010082015160089091015550807fdf3c697ef032c85a03a24bb4dce90fb70aaa6346064bea03031c3e13dac340dc60405160405180910390a2949350505050565b600081815260208190526040808220600181015460088201549192600160a060020a039091169163bff49450918590516020015260405160e060020a63ffffffff84160281526004810191909152602401602060405180830381600087803b151561081b57600080fd5b6102c65a03f1151561082c57600080fd5b5050506040518051949350505050565b60008281526020819052604090206007015482908290829060ff161561086157600080fd5b5050505050565b6000828152602081905260408082206001015484918491600160a060020a03169063be16446e9083908690516020015260405160e060020a63ffffffff84160281526004810191909152602401602060405180830381600087803b15156108ce57600080fd5b6102c65a03f115156108df57600080fd5b505050604051805134101590506108f557600080fd5b600085815260208190526040902060070154859060ff161561091657600080fd5b600086815260208190526040902060048101805491955061093a9060018301610b3e565b50600484018054869190600019810190811061095257fe5b600091825260209091200155600584018054906109729060018301610b3e565b50600584018054339190600019810190811061098a57fe5b6000918252602090912001805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a03929092169190911790555050506006018054340190555050565b600060208190529081526040902080546001820154600683015460078401546008850154600160a060020a039485169593909416936002840193600301929160ff169087565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610a5757805160ff1916838001178555610a84565b82800160010185558215610a84579182015b82811115610a84578251825591602001919060010190610a69565b50610a90929150610b67565b5090565b828054828255906000526020600020908101928215610a845791602002820182811115610a84578251825591602001919060010190610a69565b828054828255906000526020600020908101928215610b32579160200282015b82811115610b32578251825473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a039190911617825560209290920191600190910190610aee565b50610a90929150610b84565b815481835581811511610b6257600083815260209020610b62918101908301610b67565b505050565b610b8191905b80821115610a905760008155600101610b6d565b90565b610b8191905b80821115610a9057805473ffffffffffffffffffffffffffffffffffffffff19168155600101610b8a5600a165627a7a72305820aee5258ede847f7c88f49bd368c582012430bde1c559903920bf952f6136f1940029";

    protected Spleat(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Spleat(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public List<OrderOpenedEventResponse> getOrderOpenedEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("OrderOpened", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}),
                Arrays.<TypeReference<?>>asList());
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<OrderOpenedEventResponse> responses = new ArrayList<OrderOpenedEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            OrderOpenedEventResponse typedResponse = new OrderOpenedEventResponse();
            typedResponse.orderId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<OrderOpenedEventResponse> orderOpenedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("OrderOpened", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}),
                Arrays.<TypeReference<?>>asList());
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, OrderOpenedEventResponse>() {
            @Override
            public OrderOpenedEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                OrderOpenedEventResponse typedResponse = new OrderOpenedEventResponse();
                typedResponse.orderId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public RemoteCall<TransactionReceipt> makeOrder(BigInteger orderId) {
        Function function = new Function(
                "makeOrder", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(orderId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> openOrder(String restaurant, String deliveryAddress, String phone) {
        Function function = new Function(
                "openOrder", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(restaurant), 
                new org.web3j.abi.datatypes.Utf8String(deliveryAddress), 
                new org.web3j.abi.datatypes.Utf8String(phone)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> restaurantOrderStatus(BigInteger orderId) {
        Function function = new Function("restaurantOrderStatus", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(orderId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> removeItem(BigInteger orderId, BigInteger id) {
        Function function = new Function(
                "removeItem", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(orderId), 
                new org.web3j.abi.datatypes.generated.Uint256(id)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> addItem(BigInteger orderId, BigInteger id, BigInteger weiValue) {
        Function function = new Function(
                "addItem", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(orderId), 
                new org.web3j.abi.datatypes.generated.Uint256(id)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteCall<Tuple7<String, String, String, String, BigInteger, Boolean, BigInteger>> orders(BigInteger param0) {
        final Function function = new Function("orders", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Bool>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple7<String, String, String, String, BigInteger, Boolean, BigInteger>>(
                new Callable<Tuple7<String, String, String, String, BigInteger, Boolean, BigInteger>>() {
                    @Override
                    public Tuple7<String, String, String, String, BigInteger, Boolean, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);;
                        return new Tuple7<String, String, String, String, BigInteger, Boolean, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (String) results.get(2).getValue(), 
                                (String) results.get(3).getValue(), 
                                (BigInteger) results.get(4).getValue(), 
                                (Boolean) results.get(5).getValue(), 
                                (BigInteger) results.get(6).getValue());
                    }
                });
    }

    public static RemoteCall<Spleat> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Spleat.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<Spleat> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Spleat.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static Spleat load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Spleat(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static Spleat load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Spleat(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static class OrderOpenedEventResponse {
        public BigInteger orderId;
    }
}
