package org.example.demo.service;

import cn.hutool.core.lang.Dict;import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.*;
import cn.hutool.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;

public class WeBASEService {
    @Value("${system.contract.SupplyChainFinance.Address}")
    String contractAddress;
    public static final String ABI = org.example.demo.utils.IOUtil.readResourceAsString("abi/demo.abi");
    public Dict newGet(String userAddress) {
        List funcParam = new ArrayList();
        Dict result = commonReq(userAddress, "get", funcParam);
        return result;
    }
    public Dict newSet(String userAddress, String setValue) {
        List funcParam = new ArrayList();
        funcParam.add(setValue);
        Dict result = commonReq(userAddress, "set", funcParam);
        return result;
    }
    Dict commonReq(String userAddress, String funcName, List funcParam) {
        JSONArray abiJSON = JSONUtil.parseArray(ABI);
        JSONObject data = JSONUtil.createObj();
        data.set("groupId", "1");
        data.set("user", userAddress);
        data.set("contractName", "demo");
        data.set("version", "");
        data.set("funcName", funcName);
        data.set("funcParam", funcParam);
        data.set("contractAddress", contractAddress);
        data.set("contractAbi", abiJSON);
        data.set("useAes", false);
        data.set("useCns", false);
        data.set("cnsName", "");
        String dataString = JSONUtil.toJsonStr(data);
        String responseBody = HttpRequest.post("http://localhost:5002/WeBASE-Front/trans/handle")
        .header(Header.CONTENT_TYPE, "application/json").body(dataString).execute().body();
        Dict retDict = new Dict();
        retDict.set("result", responseBody);
        return retDict;
    }
    }

