import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.fisco.bcos.sdk.abi.FunctionReturnDecoder;
import org.fisco.bcos.sdk.abi.TypeReference;
import org.fisco.bcos.sdk.abi.datatypes.Address;
import org.fisco.bcos.sdk.abi.datatypes.Bool;
import org.fisco.bcos.sdk.abi.datatypes.Event;
import org.fisco.bcos.sdk.abi.datatypes.Function;
import org.fisco.bcos.sdk.abi.datatypes.Type;
import org.fisco.bcos.sdk.abi.datatypes.Utf8String;
import org.fisco.bcos.sdk.abi.datatypes.generated.Uint256;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple1;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple2;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple4;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.contract.Contract;
import org.fisco.bcos.sdk.crypto.CryptoSuite;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.eventsub.EventCallback;
import org.fisco.bcos.sdk.model.CryptoType;
import org.fisco.bcos.sdk.model.TransactionReceipt;
import org.fisco.bcos.sdk.model.callback.TransactionCallback;
import org.fisco.bcos.sdk.transaction.model.exception.ContractException;

@SuppressWarnings("unchecked")
public class SupplyChainFinance extends Contract {
    public static final String[] BINARY_ARRAY = {"6080604052600160025534801561001557600080fd5b5061120e806100256000396000f300608060405260043610610083576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff168063047ec48b146100885780631c6d6b5e146100d55780633e97b43f146101025780636ba710ca146101b4578063704f1b941461020157806396e96e201461026a578063a87430ba14610297575b600080fd5b34801561009457600080fd5b506100d360048036038101908080359060200190929190803573ffffffffffffffffffffffffffffffffffffffff16906020019092919050505061035e565b005b3480156100e157600080fd5b50610100600480360381019080803590602001909291905050506105ba565b005b34801561010e57600080fd5b5061012d600480360381019080803590602001909291905050506107ee565b604051808581526020018473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018215151515815260200194505050505060405180910390f35b3480156101c057600080fd5b506101ff60048036038101908080359060200190929190803573ffffffffffffffffffffffffffffffffffffffff16906020019092919050505061086b565b005b34801561020d57600080fd5b50610268600480360381019080803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290505050610b63565b005b34801561027657600080fd5b5061029560048036038101908080359060200190929190505050610cc0565b005b3480156102a357600080fd5b506102d8600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050611074565b604051808060200183151515158152602001828103825284818151815260200191508051906020019080838360005b83811015610322578082015181840152602081019050610307565b50505050905090810190601f16801561034f5780820380516001836020036101000a031916815260200191505b50935050505060405180910390f35b3373ffffffffffffffffffffffffffffffffffffffff166001600084815260200190815260200160002060010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614151561045d576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260258152602001807f4f6e6c79206973737565722063616e207472616e736665722074686520766f7581526020017f636865722e00000000000000000000000000000000000000000000000000000081525060400191505060405180910390fd5b6001600083815260200190815260200160002060020160149054906101000a900460ff161515156104f6576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260158152602001807f566f7563686572206973206d6f727467616765642e000000000000000000000081525060200191505060405180910390fd5b806001600084815260200190815260200160002060020160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055507f6111b3fafe19eb214f828fa63d6b614e1598f26978987151d90b497c11fd92318282604051808381526020018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019250505060405180910390a15050565b3373ffffffffffffffffffffffffffffffffffffffff166001600083815260200190815260200160002060010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff161415156106b9576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260238152602001807f4f6e6c79206973737565722063616e2072656465656d2074686520766f75636881526020017f65722e000000000000000000000000000000000000000000000000000000000081525060400191505060405180910390fd5b6001600082815260200190815260200160002060020160149054906101000a900460ff161515610751576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260168152602001807f566f7563686572206e6f74206d6f727467616765642e0000000000000000000081525060200191505060405180910390fd5b60006001600083815260200190815260200160002060020160146101000a81548160ff0219169083151502179055507f2d3099bcbed5869eea6ce6e721ef55f107c936f9243bbc491edda76242f93cfd8133604051808381526020018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019250505060405180910390a150565b60016020528060005260406000206000915090508060000154908060010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16908060020160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16908060020160149054906101000a900460ff16905084565b3373ffffffffffffffffffffffffffffffffffffffff166001600084815260200190815260200160002060020160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614151561096a576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260258152602001807f4f6e6c7920686f6c6465722063616e206d6f7274676167652074686520766f7581526020017f636865722e00000000000000000000000000000000000000000000000000000081525060400191505060405180910390fd5b60405180807f62616e6b0000000000000000000000000000000000000000000000000000000081525060040190506040518091039020600019166000808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206000016040518082805460018160011615610100020316600290048015610a405780601f10610a1e576101008083540402835291820191610a40565b820191906000526020600020905b815481529060010190602001808311610a2c575b5050915050604051809103902060001916141515610ac6576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260198152602001807f4d6f72746761676565206d75737420626520612062616e6b2e0000000000000081525060200191505060405180910390fd5b600180600084815260200190815260200160002060020160146101000a81548160ff0219169083151502179055507fbc644770ad8ff93e6f00008141381b9b67b73c8dc7e919e22ff46cded54d92c88282604051808381526020018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019250505060405180910390a15050565b6000803373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060010160009054906101000a900460ff16151515610c27576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260188152602001807f5573657220616c726561647920726567697374657265642e000000000000000081525060200191505060405180910390fd5b6040805190810160405280828152602001600115158152506000803373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206000820151816000019080519060200190610c9992919061113d565b5060208201518160010160006101000a81548160ff02191690831515021790555090505050565b60008060003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060010160009054906101000a900460ff161515610d85576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260148152602001807f55736572206e6f7420726567697374657265642e00000000000000000000000081525060200191505060405180910390fd5b60405180807f656e746572707269736500000000000000000000000000000000000000000000815250600a0190506040518091039020600019166000803373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206000016040518082805460018160011615610100020316600290048015610e5b5780601f10610e39576101008083540402835291820191610e5b565b820191906000526020600020905b815481529060010190602001808311610e47575b5050915050604051809103902060001916141515610f07576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260248152602001807f4f6e6c7920656e7465727072697365732063616e20697373756520766f75636881526020017f6572732e0000000000000000000000000000000000000000000000000000000081525060400191505060405180910390fd5b600260008154809291906001019190505590506080604051908101604052808381526020013373ffffffffffffffffffffffffffffffffffffffff1681526020013373ffffffffffffffffffffffffffffffffffffffff16815260200160001515815250600160008381526020019081526020016000206000820151816000015560208201518160010160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506040820151816002016000","6101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060608201518160020160146101000a81548160ff0219169083151502179055509050507f389c6a21bc2e71ac612c0e5a909696fa4ebe16c136cf326a53fc5e25afa177fc816040518082815260200191505060405180910390a15050565b6000602052806000526040600020600091509050806000018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156111205780601f106110f557610100808354040283529160200191611120565b820191906000526020600020905b81548152906001019060200180831161110357829003601f168201915b5050505050908060010160009054906101000a900460ff16905082565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061117e57805160ff19168380011785556111ac565b828001600101855582156111ac579182015b828111156111ab578251825591602001919060010190611190565b5b5090506111b991906111bd565b5090565b6111df91905b808211156111db5760008160009055506001016111c3565b5090565b905600a165627a7a72305820b67d2713020510f13459f2cf51a4bcde0aa84ae5b2ac7b2338cb80227259811d0029"};

    public static final String BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", BINARY_ARRAY);

    public static final String[] SM_BINARY_ARRAY = {};

    public static final String SM_BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", SM_BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"constant\":false,\"inputs\":[{\"name\":\"voucherId\",\"type\":\"uint256\"},{\"name\":\"to\",\"type\":\"address\"}],\"name\":\"transferVoucher\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"voucherId\",\"type\":\"uint256\"}],\"name\":\"redeemVoucher\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"name\":\"vouchers\",\"outputs\":[{\"name\":\"amount\",\"type\":\"uint256\"},{\"name\":\"issuer\",\"type\":\"address\"},{\"name\":\"currentHolder\",\"type\":\"address\"},{\"name\":\"isMortgaged\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"voucherId\",\"type\":\"uint256\"},{\"name\":\"toBank\",\"type\":\"address\"}],\"name\":\"mortgageVoucher\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"userType\",\"type\":\"string\"}],\"name\":\"registerUser\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"amount\",\"type\":\"uint256\"}],\"name\":\"createVoucher\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"\",\"type\":\"address\"}],\"name\":\"users\",\"outputs\":[{\"name\":\"userType\",\"type\":\"string\"},{\"name\":\"isRegistered\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"voucherId\",\"type\":\"uint256\"}],\"name\":\"VoucherCreated\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"voucherId\",\"type\":\"uint256\"},{\"indexed\":false,\"name\":\"to\",\"type\":\"address\"}],\"name\":\"VoucherTransferred\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"voucherId\",\"type\":\"uint256\"},{\"indexed\":false,\"name\":\"toBank\",\"type\":\"address\"}],\"name\":\"VoucherMortgaged\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"voucherId\",\"type\":\"uint256\"},{\"indexed\":false,\"name\":\"byEnterprise\",\"type\":\"address\"}],\"name\":\"VoucherRedeemed\",\"type\":\"event\"}]"};

    public static final String ABI = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", ABI_ARRAY);

    public static final String FUNC_TRANSFERVOUCHER = "transferVoucher";

    public static final String FUNC_REDEEMVOUCHER = "redeemVoucher";

    public static final String FUNC_VOUCHERS = "vouchers";

    public static final String FUNC_MORTGAGEVOUCHER = "mortgageVoucher";

    public static final String FUNC_REGISTERUSER = "registerUser";

    public static final String FUNC_CREATEVOUCHER = "createVoucher";

    public static final String FUNC_USERS = "users";

    public static final Event VOUCHERCREATED_EVENT = new Event("VoucherCreated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    public static final Event VOUCHERTRANSFERRED_EVENT = new Event("VoucherTransferred", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Address>() {}));
    ;

    public static final Event VOUCHERMORTGAGED_EVENT = new Event("VoucherMortgaged", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Address>() {}));
    ;

    public static final Event VOUCHERREDEEMED_EVENT = new Event("VoucherRedeemed", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Address>() {}));
    ;

    protected SupplyChainFinance(String contractAddress, Client client, CryptoKeyPair credential) {
        super(getBinary(client.getCryptoSuite()), contractAddress, client, credential);
    }

    public static String getBinary(CryptoSuite cryptoSuite) {
        return (cryptoSuite.getCryptoTypeConfig() == CryptoType.ECDSA_TYPE ? BINARY : SM_BINARY);
    }

    public TransactionReceipt transferVoucher(BigInteger voucherId, String to) {
        final Function function = new Function(
                FUNC_TRANSFERVOUCHER, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(voucherId), 
                new org.fisco.bcos.sdk.abi.datatypes.Address(to)), 
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public void transferVoucher(BigInteger voucherId, String to, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_TRANSFERVOUCHER, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(voucherId), 
                new org.fisco.bcos.sdk.abi.datatypes.Address(to)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForTransferVoucher(BigInteger voucherId, String to) {
        final Function function = new Function(
                FUNC_TRANSFERVOUCHER, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(voucherId), 
                new org.fisco.bcos.sdk.abi.datatypes.Address(to)), 
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple2<BigInteger, String> getTransferVoucherInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_TRANSFERVOUCHER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Address>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple2<BigInteger, String>(

                (BigInteger) results.get(0).getValue(), 
                (String) results.get(1).getValue()
                );
    }

    public TransactionReceipt redeemVoucher(BigInteger voucherId) {
        final Function function = new Function(
                FUNC_REDEEMVOUCHER, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(voucherId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public void redeemVoucher(BigInteger voucherId, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_REDEEMVOUCHER, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(voucherId)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForRedeemVoucher(BigInteger voucherId) {
        final Function function = new Function(
                FUNC_REDEEMVOUCHER, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(voucherId)), 
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple1<BigInteger> getRedeemVoucherInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_REDEEMVOUCHER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
                );
    }

    public Tuple4<BigInteger, String, String, Boolean> vouchers(BigInteger param0) throws ContractException {
        final Function function = new Function(FUNC_VOUCHERS, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Bool>() {}));
        List<Type> results = executeCallWithMultipleValueReturn(function);
        return new Tuple4<BigInteger, String, String, Boolean>(
                (BigInteger) results.get(0).getValue(), 
                (String) results.get(1).getValue(), 
                (String) results.get(2).getValue(), 
                (Boolean) results.get(3).getValue());
    }

    public TransactionReceipt mortgageVoucher(BigInteger voucherId, String toBank) {
        final Function function = new Function(
                FUNC_MORTGAGEVOUCHER, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(voucherId), 
                new org.fisco.bcos.sdk.abi.datatypes.Address(toBank)), 
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public void mortgageVoucher(BigInteger voucherId, String toBank, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_MORTGAGEVOUCHER, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(voucherId), 
                new org.fisco.bcos.sdk.abi.datatypes.Address(toBank)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForMortgageVoucher(BigInteger voucherId, String toBank) {
        final Function function = new Function(
                FUNC_MORTGAGEVOUCHER, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(voucherId), 
                new org.fisco.bcos.sdk.abi.datatypes.Address(toBank)), 
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple2<BigInteger, String> getMortgageVoucherInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_MORTGAGEVOUCHER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Address>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple2<BigInteger, String>(

                (BigInteger) results.get(0).getValue(), 
                (String) results.get(1).getValue()
                );
    }

    public TransactionReceipt registerUser(String userType) {
        final Function function = new Function(
                FUNC_REGISTERUSER, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Utf8String(userType)), 
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public void registerUser(String userType, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_REGISTERUSER, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Utf8String(userType)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForRegisterUser(String userType) {
        final Function function = new Function(
                FUNC_REGISTERUSER, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Utf8String(userType)), 
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple1<String> getRegisterUserInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_REGISTERUSER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<String>(

                (String) results.get(0).getValue()
                );
    }

    public TransactionReceipt createVoucher(BigInteger amount) {
        final Function function = new Function(
                FUNC_CREATEVOUCHER, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public void createVoucher(BigInteger amount, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_CREATEVOUCHER, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForCreateVoucher(BigInteger amount) {
        final Function function = new Function(
                FUNC_CREATEVOUCHER, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple1<BigInteger> getCreateVoucherInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_CREATEVOUCHER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
                );
    }

    public Tuple2<String, Boolean> users(String param0) throws ContractException {
        final Function function = new Function(FUNC_USERS, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Address(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Bool>() {}));
        List<Type> results = executeCallWithMultipleValueReturn(function);
        return new Tuple2<String, Boolean>(
                (String) results.get(0).getValue(), 
                (Boolean) results.get(1).getValue());
    }

    public List<VoucherCreatedEventResponse> getVoucherCreatedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(VOUCHERCREATED_EVENT, transactionReceipt);
        ArrayList<VoucherCreatedEventResponse> responses = new ArrayList<VoucherCreatedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            VoucherCreatedEventResponse typedResponse = new VoucherCreatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.voucherId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void subscribeVoucherCreatedEvent(String fromBlock, String toBlock, List<String> otherTopics, EventCallback callback) {
        String topic0 = eventEncoder.encode(VOUCHERCREATED_EVENT);
        subscribeEvent(ABI,BINARY,topic0,fromBlock,toBlock,otherTopics,callback);
    }

    public void subscribeVoucherCreatedEvent(EventCallback callback) {
        String topic0 = eventEncoder.encode(VOUCHERCREATED_EVENT);
        subscribeEvent(ABI,BINARY,topic0,callback);
    }

    public List<VoucherTransferredEventResponse> getVoucherTransferredEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(VOUCHERTRANSFERRED_EVENT, transactionReceipt);
        ArrayList<VoucherTransferredEventResponse> responses = new ArrayList<VoucherTransferredEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            VoucherTransferredEventResponse typedResponse = new VoucherTransferredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.voucherId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.to = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void subscribeVoucherTransferredEvent(String fromBlock, String toBlock, List<String> otherTopics, EventCallback callback) {
        String topic0 = eventEncoder.encode(VOUCHERTRANSFERRED_EVENT);
        subscribeEvent(ABI,BINARY,topic0,fromBlock,toBlock,otherTopics,callback);
    }

    public void subscribeVoucherTransferredEvent(EventCallback callback) {
        String topic0 = eventEncoder.encode(VOUCHERTRANSFERRED_EVENT);
        subscribeEvent(ABI,BINARY,topic0,callback);
    }

    public List<VoucherMortgagedEventResponse> getVoucherMortgagedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(VOUCHERMORTGAGED_EVENT, transactionReceipt);
        ArrayList<VoucherMortgagedEventResponse> responses = new ArrayList<VoucherMortgagedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            VoucherMortgagedEventResponse typedResponse = new VoucherMortgagedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.voucherId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.toBank = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void subscribeVoucherMortgagedEvent(String fromBlock, String toBlock, List<String> otherTopics, EventCallback callback) {
        String topic0 = eventEncoder.encode(VOUCHERMORTGAGED_EVENT);
        subscribeEvent(ABI,BINARY,topic0,fromBlock,toBlock,otherTopics,callback);
    }

    public void subscribeVoucherMortgagedEvent(EventCallback callback) {
        String topic0 = eventEncoder.encode(VOUCHERMORTGAGED_EVENT);
        subscribeEvent(ABI,BINARY,topic0,callback);
    }

    public List<VoucherRedeemedEventResponse> getVoucherRedeemedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(VOUCHERREDEEMED_EVENT, transactionReceipt);
        ArrayList<VoucherRedeemedEventResponse> responses = new ArrayList<VoucherRedeemedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            VoucherRedeemedEventResponse typedResponse = new VoucherRedeemedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.voucherId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.byEnterprise = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void subscribeVoucherRedeemedEvent(String fromBlock, String toBlock, List<String> otherTopics, EventCallback callback) {
        String topic0 = eventEncoder.encode(VOUCHERREDEEMED_EVENT);
        subscribeEvent(ABI,BINARY,topic0,fromBlock,toBlock,otherTopics,callback);
    }

    public void subscribeVoucherRedeemedEvent(EventCallback callback) {
        String topic0 = eventEncoder.encode(VOUCHERREDEEMED_EVENT);
        subscribeEvent(ABI,BINARY,topic0,callback);
    }

    public static SupplyChainFinance load(String contractAddress, Client client, CryptoKeyPair credential) {
        return new SupplyChainFinance(contractAddress, client, credential);
    }

    public static SupplyChainFinance deploy(Client client, CryptoKeyPair credential) throws ContractException {
        return deploy(SupplyChainFinance.class, client, credential, getBinary(client.getCryptoSuite()), "");
    }

    public static class VoucherCreatedEventResponse {
        public TransactionReceipt.Logs log;

        public BigInteger voucherId;
    }

    public static class VoucherTransferredEventResponse {
        public TransactionReceipt.Logs log;

        public BigInteger voucherId;

        public String to;
    }

    public static class VoucherMortgagedEventResponse {
        public TransactionReceipt.Logs log;

        public BigInteger voucherId;

        public String toBank;
    }

    public static class VoucherRedeemedEventResponse {
        public TransactionReceipt.Logs log;

        public BigInteger voucherId;

        public String byEnterprise;
    }
}
