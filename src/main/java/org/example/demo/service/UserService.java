package org.example.demo.service;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.HexUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.example.demo.utils.WeBASEUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.math.BigInteger;

@Service
public class UserService {

    @Value("${system.contract.supplyChainFinanceAddress}")
    private String contractAddress;

    public static final String ABI = org.example.demo.utils.IOUtil.readResourceAsString("abi/SupplyChainFinance.abi");

    @Autowired
    private WeBASEUtils weBASEUtils;

    public Dict registerUser(String userAddress, String userType) {
        List<Object> funcParam = new ArrayList<>();
        funcParam.add(userType);
        return weBASEUtils.commonReq(userAddress, "registerUser", funcParam, ABI, "SupplyChainFinance", contractAddress);
    }

    public Dict createVoucher(String userAddress, BigInteger amount) {
        List<Object> funcParam = new ArrayList<>();
        funcParam.add(amount);
        return weBASEUtils.commonReq(userAddress, "createVoucher", funcParam, ABI, "SupplyChainFinance", contractAddress);
    }

    public Dict transferVoucher(String userAddress, BigInteger voucherId, String toAddress) {
        List<Object> funcParam = new ArrayList<>();
        funcParam.add(voucherId);
        funcParam.add(toAddress);
        return weBASEUtils.commonReq(userAddress, "transferVoucher", funcParam, ABI, "SupplyChainFinance", contractAddress);
    }

    public Dict mortgageVoucher(String userAddress, BigInteger voucherId, String toBank) {
        List<Object> funcParam = new ArrayList<>();
        funcParam.add(voucherId);
        funcParam.add(toBank);
        return weBASEUtils.commonReq(userAddress, "mortgageVoucher", funcParam, ABI, "SupplyChainFinance", contractAddress);
    }

    public Dict redeemVoucher(String userAddress, BigInteger voucherId) {
        List<Object> funcParam = new ArrayList<>();
        funcParam.add(voucherId);
        return weBASEUtils.commonReq(userAddress, "redeemVoucher", funcParam, ABI, "SupplyChainFinance", contractAddress);
    }
}

