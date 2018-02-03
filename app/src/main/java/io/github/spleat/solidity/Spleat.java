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
import org.web3j.abi.datatypes.DynamicArray;
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
import org.web3j.tuples.generated.Tuple4;
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
    private static final String BINARY = "6060604052341561000f57600080fd5b6110a78061001e6000396000f3006060604052600436106100695763ffffffff60e060020a6000350416631f0cbfb9811461006e57806326e5f7851461008657806375b2100a14610139578063870fc347146101735780638d77eba51461018c578063a212523214610254578063a85c38ef14610262575b600080fd5b341561007957600080fd5b6100846004356103ae565b005b341561009157600080fd5b61012760048035600160a060020a03169060446024803590810190830135806020601f8201819004810201604051908101604052818152929190602084018383808284378201915050505050509190803590602001908201803590602001908080601f0160208091040260200160405190810160405281815292919060208401838380828437509496506105e095505050505050565b60405190815260200160405180910390f35b341561014457600080fd5b61014f6004356108ab565b6040518082600481111561015f57fe5b60ff16815260200191505060405180910390f35b341561017e57600080fd5b610084600435602435610936565b341561019757600080fd5b6101a2600435610c0c565b6040518215156040820152600160a060020a0382166060820152608080825281906020820190820187818151815260200191508051906020019060200280838360005b838110156101fd5780820151838201526020016101e5565b50505050905001838103825286818151815260200191508051906020019060200280838360005b8381101561023c578082015183820152602001610224565b50505050905001965050505050505060405180910390f35b610084600435602435610d0b565b341561026d57600080fd5b610278600435610e9e565b604051600160a060020a038881168252871660208201526080810184905282151560a082015260c0810182905260e060408201818152875460026001821615610100908102600019019092160492840183905290916060840191840190899080156103245780601f106102f957610100808354040283529160200191610324565b820191906000526020600020905b81548152906001019060200180831161030757829003601f168201915b50508381038252875460026000196101006001841615020190911604808252602090910190889080156103985780601f1061036d57610100808354040283529160200191610398565b820191906000526020600020905b81548152906001019060200180831161037b57829003601f168201915b5050995050505050505050505060405180910390f35b6000818152602081905260408120548190839033600160a060020a039081169116146103d957600080fd5b600084815260208190526040902060080154849060ff16156103fa57600080fd5b60008581526020819052604080822060018101546007820154919750600160a060020a03169263852d599792600289019160038a019160048b019190516020015260405160e060020a63ffffffff8716028152606060048201908152845460026000196101006001841615020190911604606483018190529091829160248201916044810191608490910190889080156104d55780601f106104aa576101008083540402835291602001916104d5565b820191906000526020600020905b8154815290600101906020018083116104b857829003601f168201915b50508481038352865460026000196101006001841615020190911604808252602090910190879080156105495780601f1061051e57610100808354040283529160200191610549565b820191906000526020600020905b81548152906001019060200180831161052c57829003601f168201915b5050848103825285818154815260200191508054801561058857602002820191906000526020600020905b815481526020019060010190808311610574575b505096505050505050506020604051808303818588803b15156105aa57600080fd5b6125ee5a03f115156105bb57600080fd5b50505050604051805160098601555050506008909101805460ff191660011790555050565b6000808484846040516c01000000000000000000000000600160a060020a0385160281526014810183805190602001908083835b602083106106335780518252601f199092019160209182019101610614565b6001836020036101000a038019825116818451161790925250505091909101905082805190602001908083835b6020831061067f5780518252601f199092019160209182019101610660565b6001836020036101000a038019825116818451161790925250505091909101945060409350505050519081900390206000194301401890506101406040519081016040528033600160a060020a0316815260200186600160a060020a0316815260200185815260200184815260200160006040518059106106fd5750595b9080825280602002602001820160405250815260200160006040518059106107225750595b9080825280602002602001820160405250815260200160006040518059106107475750595b9080825280602002602001820160405250815260006020808301829052604080840183905260609093018290528482528190522081518154600160a060020a031916600160a060020a03919091161781556020820151600182018054600160a060020a031916600160a060020a03929092169190911790556040820151816002019080516107d9929160200190610ee4565b506060820151816003019080516107f4929160200190610ee4565b5060808201518160040190805161080f929160200190610f62565b5060a08201518160050190805161082a929160200190610f9c565b5060c082015181600601908051610845929160200190610f62565b5060e0820151816007015561010082015160088201805460ff191691151591909117905561012082015160099091015550807fdf3c697ef032c85a03a24bb4dce90fb70aaa6346064bea03031c3e13dac340dc60405160405180910390a2949350505050565b600081815260208190526040808220600181015460098201549192600160a060020a039091169163bff49450918590516020015260405160e060020a63ffffffff84160281526004810191909152602401602060405180830381600087803b151561091557600080fd5b6102c65a03f1151561092657600080fd5b5050506040518051949350505050565b600082815260208190526040812081908490849083805b60048301548110156109c75783836004018281548110151561096b57fe5b9060005260206000209001541480156109b1575033600160a060020a0316836005018281548110151561099a57fe5b600091825260209091200154600160a060020a0316145b156109bf57600191506109c7565b60010161094d565b8115156109d357600080fd5b600089815260208190526040902060080154899060ff16156109f457600080fd5b60008a8152602081905260408120985096505b6004880154871015610c0057888860040188815481101515610a2557fe5b906000526020600020900154148015610a6b575033600160a060020a03168860050188815481101515610a5457fe5b600091825260209091200154600160a060020a0316145b15610bf55733600160a060020a03166108fc8960060189815481101515610a8e57fe5b9060005260206000209001549081150290604051600060405180830381858888f193505050501515610abf57600080fd5b6004880154600019018714610bae576004880180546000198101908110610ae257fe5b9060005260206000209001548860040188815481101515610aff57fe5b6000918252602090912001556005880180546000198101908110610b1f57fe5b600091825260209091200154600589018054600160a060020a039092169189908110610b4757fe5b60009182526020909120018054600160a060020a031916600160a060020a03929092169190911790556006880180546000198101908110610b8457fe5b9060005260206000209001548860060188815481101515610ba157fe5b6000918252602090912001555b60048801805490610bc3906000198301610fff565b5060058801805490610bd9906000198301610fff565b5060068801805490610bef906000198301610fff565b50610c00565b600190960195610a07565b50505050505050505050565b610c14611028565b610c1c611028565b60008381526020818152604080832060088101548154600483018054879694959194600587019460ff1693600160a060020a031692869291828102019051908101604052809291908181526020018280548015610c9857602002820191906000526020600020905b815481526020019060010190808311610c84575b5050505050935082805480602002602001604051908101604052809291908181526020018280548015610cf457602002820191906000526020600020905b8154600160a060020a03168152600190910190602001808311610cd6575b505050505092509450945094509450509193509193565b6000828152602081905260408082206001015484918491600160a060020a03169063be16446e9083908690516020015260405160e060020a63ffffffff84160281526004810191909152602401602060405180830381600087803b1515610d7157600080fd5b6102c65a03f11515610d8257600080fd5b50505060405180513410159050610d9857600080fd5b600085815260208190526040902060080154859060ff1615610db957600080fd5b6000868152602081905260409020600481018054919550610ddd9060018301610fff565b506004840180548691906000198101908110610df557fe5b60009182526020909120015560058401805490610e159060018301610fff565b506005840180543391906000198101908110610e2d57fe5b60009182526020909120018054600160a060020a031916600160a060020a039290921691909117905560068401805490610e6a9060018301610fff565b506006840180543491906000198101908110610e8257fe5b6000918252602090912001555050506007018054340190555050565b600060208190529081526040902080546001820154600783015460088401546009850154600160a060020a039485169593909416936002840193600301929160ff169087565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610f2557805160ff1916838001178555610f52565b82800160010185558215610f52579182015b82811115610f52578251825591602001919060010190610f37565b50610f5e92915061103a565b5090565b828054828255906000526020600020908101928215610f525791602002820182811115610f52578251825591602001919060010190610f37565b828054828255906000526020600020908101928215610ff3579160200282015b82811115610ff35782518254600160a060020a031916600160a060020a039190911617825560209290920191600190910190610fbc565b50610f5e929150611057565b8154818355818115116110235760008381526020902061102391810190830161103a565b505050565b60206040519081016040526000815290565b61105491905b80821115610f5e5760008155600101611040565b90565b61105491905b80821115610f5e578054600160a060020a031916815560010161105d5600a165627a7a723058207f0633b2058936b2922baae5b88a229922bef94d32b902e9b7d4a3506a0a3bcf0029";

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

    public RemoteCall<Tuple4<List<BigInteger>, List<String>, Boolean, String>> orderById(BigInteger orderId) {
        final Function function = new Function("orderById", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(orderId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<DynamicArray<Address>>() {}, new TypeReference<Bool>() {}, new TypeReference<Address>() {}));
        return new RemoteCall<Tuple4<List<BigInteger>, List<String>, Boolean, String>>(
                new Callable<Tuple4<List<BigInteger>, List<String>, Boolean, String>>() {
                    @Override
                    public Tuple4<List<BigInteger>, List<String>, Boolean, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);;
                        return new Tuple4<List<BigInteger>, List<String>, Boolean, String>(
                                (List<BigInteger>) results.get(0).getValue(), 
                                (List<String>) results.get(1).getValue(), 
                                (Boolean) results.get(2).getValue(), 
                                (String) results.get(3).getValue());
                    }
                });
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
