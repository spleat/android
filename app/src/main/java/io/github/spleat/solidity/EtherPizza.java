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
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple3;
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
public class EtherPizza extends Contract {
    private static final String BINARY = "606060405260008054600160a060020a03191633600160a060020a03161790556001805490620000329082810162000039565b5062000111565b81548183558181151162000068576003028160030283600052602060002091820191016200006891906200006d565b505050565b620000a691905b80821115620000a2576000808255620000916001830182620000a9565b506000600282015560030162000074565b5090565b90565b50805460018160011615610100020316600290046000825580601f10620000d15750620000f1565b601f016020900490600052602060002090810190620000f19190620000f4565b50565b620000a691905b80821115620000a25760008155600101620000fb565b6111d980620001216000396000f3006060604052600436106100c45763ffffffff7c01000000000000000000000000000000000000000000000000000000006000350416630d94bed581146100c95780631036348f146100e15780631b8c556e146101425780633a1b3d31146101955780633d939a49146101b157806345c35f2f146101c4578063852d599714610260578063a85c38ef14610328578063be16446e1461045a578063bff4945014610470578063cffb703c146104aa578063d2ae963714610557578063ea3cff8214610648575b600080fd5b34156100d457600080fd5b6100df60043561065b565b005b34156100ec57600080fd5b610130600460248135818101908301358060208181020160405190810160405280939291908181526020018383602002808284375094965061075a95505050505050565b60405190815260200160405180910390f35b341561014d57600080fd5b6100df60046024813581810190830135806020601f8201819004810201604051908101604052818152929190602084018383808284375094965050933593506107d992505050565b34156101a057600080fd5b6100df60043560ff60243516610918565b34156101bc57600080fd5b6101306109cc565b34156101cf57600080fd5b6101da6004356109d7565b6040518084815260200180602001838152602001828103825284818151815260200191508051906020019080838360005b8381101561022357808201518382015260200161020b565b50505050905090810190601f1680156102505780820380516001836020036101000a031916815260200191505b5094505050505060405180910390f35b61013060046024813581810190830135806020601f8201819004810201604051908101604052818152929190602084018383808284378201915050505050509190803590602001908201803590602001908080601f01602080910402602001604051908101604052818152929190602084018383808284378201915050505050509190803590602001908201803590602001908080602002602001604051908101604052809392919081815260200183836020028082843750949650610acf95505050505050565b341561033357600080fd5b61033e600435610c00565b60405180806020018060200184600481111561035657fe5b60ff168152602084820381018452875460026001821615610100026000190190911604908201819052604090910190879080156103d45780601f106103a9576101008083540402835291602001916103d4565b820191906000526020600020905b8154815290600101906020018083116103b757829003601f168201915b50508381038252855460026000196101006001841615020190911604808252602090910190869080156104485780601f1061041d57610100808354040283529160200191610448565b820191906000526020600020905b81548152906001019060200180831161042b57829003601f168201915b50509550505050505060405180910390f35b341561046557600080fd5b610130600435610c31565b341561047b57600080fd5b610486600435610c71565b6040518082600481111561049657fe5b60ff16815260200191505060405180910390f35b34156104b557600080fd5b6104c0600435610c9f565b60405183815260408101829052606060208201818152845460026000196101006001841615020190911604918301829052906080830190859080156105465780601f1061051b57610100808354040283529160200191610546565b820191906000526020600020905b81548152906001019060200180831161052957829003601f168201915b505094505050505060405180910390f35b341561056257600080fd5b61056a610cd0565b60405180806020018060200180602001848103845287818151815260200191508051906020019060200280838360005b838110156105b257808201518382015260200161059a565b50505050905001848103835286818151815260200191508051906020019060200280838360005b838110156105f15780820151838201526020016105d9565b50505050905001848103825285818151815260200191508051906020019060200280838360005b83811015610630578082015183820152602001610618565b50505050905001965050505050505060405180910390f35b341561065357600080fd5b6100df610ebd565b60008054819033600160a060020a0390811691161461067957600080fd5b600083815260026020526040902054915081151561069357fe5b600083815260026020526040812055600154600019018214610741576001805460001981019081106106c157fe5b6000918252602080832060039092029091018054835260029091526040909120839055600180549192508291849081106106f757fe5b90600052602060002090600302016000820154816000015560018201816001019080546001816001161561010002031660029004610736929190610f17565b506002918201549101555b6001805490610754906000198301610f9c565b50505050565b60008080805b84518210156107d0576002600086848151811061077957fe5b906020019060200201518152602081019190915260400160002054905080151561079f57fe5b60018054829081106107ad57fe5b906000526020600020906003020160020154830192508180600101925050610760565b50909392505050565b60008054819033600160a060020a039081169116146107f757600080fd5b60015491508184846040518084815260200183805190602001908083835b602083106108345780518252601f199092019160209182019101610815565b6001836020036101000a038019825116818451161790925250505091909101928352505060200191506040905051908190039020600019430140186000818152600260205260409020549091501561088857fe5b600081815260026020526040902082905560018054906108aa90828101610f9c565b50606060405190810160405280828152602001858152602001848152506001838154811015156108d657fe5b906000526020600020906003020160008201518155602082015181600101908051610905929160200190610fcd565b5060408201516002909101555050505050565b60005433600160a060020a0390811691161461093357600080fd5b600354821061094157600080fd5b8060038381548110151561095157fe5b906000526020600020906004020160020160006101000a81548160ff0219169083600481111561097d57fe5b0217905550817fbf2b4642c855d594ee27c616fdb79b37946a4f3405e837fc838077150806b10082604051808260048111156109b557fe5b60ff16815260200191505060405180910390a25050565b600154600019015b90565b60006109e161103b565b6000806109ec6109cc565b85106109f757600080fd5b60018054868201908110610a0757fe5b906000526020600020906003020190508060000154816001018260020154818054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610aba5780601f10610a8f57610100808354040283529160200191610aba565b820191906000526020600020905b815481529060010190602001808311610a9d57829003601f168201915b50505050509150935093509350509193909250565b60008082610adc8161075a565b341015610ae857600080fd5b6003805492508290610afd906001830161104d565b5060806040519081016040908152878252602082018790528101600081526020018590526003805484908110610b2f57fe5b9060005260206000209060040201600082015181908051610b54929160200190610fcd565b50602082015181600101908051610b6f929160200190610fcd565b50604082015160028201805460ff19166001836004811115610b8d57fe5b0217905550606082015181600301908051610bac929160200190611079565b50905050817fbf2b4642c855d594ee27c616fdb79b37946a4f3405e837fc838077150806b100600060405180826004811115610be457fe5b60ff16815260200191505060405180910390a250949350505050565b6003805482908110610c0e57fe5b600091825260209091206004909102016002810154909150600182019060ff1683565b600081815260026020526040812054801515610c4957fe5b6001805482908110610c5757fe5b906000526020600020906003020160020154915050919050565b6000600382815481101515610c8257fe5b600091825260209091206004909102016002015460ff1692915050565b6001805482908110610cad57fe5b600091825260209091206003909102018054600282015490925060019091019083565b610cd861103b565b610ce061103b565b610ce861103b565b6000610cf261103b565b610cfa61103b565b610d0261103b565b600080610d0d61103b565b6000610d176109cc565b975087604051805910610d275750595b9080825280602002602001820160405250965087604051805910610d485750595b9080825280602002602001820160405250955087604051805910610d695750595b90808252806020026020018201604052509450600093505b87841015610eab5760018054858201908110610d9957fe5b906000526020600020906003020192508260000154878581518110610dba57fe5b9060200190602002018181525050826001018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610e605780601f10610e3557610100808354040283529160200191610e60565b820191906000526020600020905b815481529060010190602001808311610e4357829003601f168201915b505050505091506020820151905080868581518110610e7b57fe5b602090810290910101526002830154858581518110610e9657fe5b60209081029091010152600190930192610d81565b50949993985091965091945050505050565b60005433600160a060020a03908116911614610ed857600080fd5b33600160a060020a03166108fc30600160a060020a0316319081150290604051600060405180830381858888f193505050501515610f1557600080fd5b565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610f505780548555610f8c565b82800160010185558215610f8c57600052602060002091601f016020900482015b82811115610f8c578254825591600101919060010190610f71565b50610f989291506110b3565b5090565b815481835581811511610fc857600302816003028360005260206000209182019101610fc891906110cd565b505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061100e57805160ff1916838001178555610f8c565b82800160010185558215610f8c579182015b82811115610f8c578251825591602001919060010190611020565b60206040519081016040526000815290565b815481835581811511610fc857600402816004028360005260206000209182019101610fc891906110fd565b828054828255906000526020600020908101928215610f8c5791602002820182811115610f8c578251825591602001919060010190611020565b6109d491905b80821115610f9857600081556001016110b9565b6109d491905b80821115610f985760008082556110ed6001830182611148565b50600060028201556003016110d3565b6109d491905b80821115610f985760006111178282611148565b611125600183016000611148565b60028201805460ff1916905561113f60038301600061118f565b50600401611103565b50805460018160011615610100020316600290046000825580601f1061116e575061118c565b601f01602090049060005260206000209081019061118c91906110b3565b50565b508054600082559060005260206000209081019061118c91906110b35600a165627a7a72305820695734da6829b1e921bfcdbbbe14881a4fc4255ba9698936e5bfc7d4ecff02190029";

    protected EtherPizza(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected EtherPizza(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public List<OrderStatusUpdateEventResponse> getOrderStatusUpdateEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("OrderStatusUpdate", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<OrderStatusUpdateEventResponse> responses = new ArrayList<OrderStatusUpdateEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            OrderStatusUpdateEventResponse typedResponse = new OrderStatusUpdateEventResponse();
            typedResponse.id = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.status = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<OrderStatusUpdateEventResponse> orderStatusUpdateEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("OrderStatusUpdate", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, OrderStatusUpdateEventResponse>() {
            @Override
            public OrderStatusUpdateEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                OrderStatusUpdateEventResponse typedResponse = new OrderStatusUpdateEventResponse();
                typedResponse.id = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.status = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public RemoteCall<TransactionReceipt> removeMenuItem(BigInteger id) {
        Function function = new Function(
                "removeMenuItem", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(id)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> calculateCost(List<BigInteger> items) {
        Function function = new Function("calculateCost", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Uint256>(
                        org.web3j.abi.Utils.typeMap(items, org.web3j.abi.datatypes.generated.Uint256.class))), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> addMenuItem(String description, BigInteger price) {
        Function function = new Function(
                "addMenuItem", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(description), 
                new org.web3j.abi.datatypes.generated.Uint256(price)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> updateStatus(BigInteger id, BigInteger status) {
        Function function = new Function(
                "updateStatus", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(id), 
                new org.web3j.abi.datatypes.generated.Uint8(status)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> menuLength() {
        Function function = new Function("menuLength", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<Tuple3<BigInteger, String, BigInteger>> menuItem(BigInteger index) {
        final Function function = new Function("menuItem", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(index)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple3<BigInteger, String, BigInteger>>(
                new Callable<Tuple3<BigInteger, String, BigInteger>>() {
                    @Override
                    public Tuple3<BigInteger, String, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);;
                        return new Tuple3<BigInteger, String, BigInteger>(
                                (BigInteger) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue());
                    }
                });
    }

    public RemoteCall<TransactionReceipt> order(String deliveryAddress, String phone, List<BigInteger> items, BigInteger weiValue) {
        Function function = new Function(
                "order", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(deliveryAddress), 
                new org.web3j.abi.datatypes.Utf8String(phone), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Uint256>(
                        org.web3j.abi.Utils.typeMap(items, org.web3j.abi.datatypes.generated.Uint256.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteCall<Tuple3<String, String, BigInteger>> orders(BigInteger param0) {
        final Function function = new Function("orders", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint8>() {}));
        return new RemoteCall<Tuple3<String, String, BigInteger>>(
                new Callable<Tuple3<String, String, BigInteger>>() {
                    @Override
                    public Tuple3<String, String, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);;
                        return new Tuple3<String, String, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue());
                    }
                });
    }

    public RemoteCall<BigInteger> menuItemPrice(BigInteger id) {
        Function function = new Function("menuItemPrice", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(id)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> orderStatus(BigInteger id) {
        Function function = new Function("orderStatus", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(id)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<Tuple3<BigInteger, String, BigInteger>> menu(BigInteger param0) {
        final Function function = new Function("menu", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple3<BigInteger, String, BigInteger>>(
                new Callable<Tuple3<BigInteger, String, BigInteger>>() {
                    @Override
                    public Tuple3<BigInteger, String, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);;
                        return new Tuple3<BigInteger, String, BigInteger>(
                                (BigInteger) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue());
                    }
                });
    }

    public RemoteCall<Tuple3<List<BigInteger>, List<byte[]>, List<BigInteger>>> wholeMenu() {
        final Function function = new Function("wholeMenu", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<DynamicArray<Bytes32>>() {}, new TypeReference<DynamicArray<Uint256>>() {}));
        return new RemoteCall<Tuple3<List<BigInteger>, List<byte[]>, List<BigInteger>>>(
                new Callable<Tuple3<List<BigInteger>, List<byte[]>, List<BigInteger>>>() {
                    @Override
                    public Tuple3<List<BigInteger>, List<byte[]>, List<BigInteger>> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);;
                        return new Tuple3<List<BigInteger>, List<byte[]>, List<BigInteger>>(
                                (List<BigInteger>) results.get(0).getValue(), 
                                (List<byte[]>) results.get(1).getValue(), 
                                (List<BigInteger>) results.get(2).getValue());
                    }
                });
    }

    public RemoteCall<TransactionReceipt> collectLoot() {
        Function function = new Function(
                "collectLoot", 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static RemoteCall<EtherPizza> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(EtherPizza.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<EtherPizza> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(EtherPizza.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static EtherPizza load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new EtherPizza(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static EtherPizza load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new EtherPizza(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static class OrderStatusUpdateEventResponse {
        public BigInteger id;

        public BigInteger status;
    }
}
