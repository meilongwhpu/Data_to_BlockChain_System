package io.nuls.data.rpc.impl;

import io.nuls.data.constant.SystemDictKey;
import io.nuls.data.pojo.po.VerifyDataPO;
import io.nuls.data.rpc.ChainBlockService;
import io.nuls.data.service.SystemDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ChainBlockForNulsNetService implements ChainBlockService {
    @Autowired
    private HttpClient httpClient;

    @Autowired
    private SystemDictService systemDictService;

    public String saveData(String address, String password,String remark) throws Exception {
        int chainId=Integer.parseInt(systemDictService.findValueByKey(SystemDictKey.NULS_NET_CHAINID));
        int assetId=Integer.parseInt(systemDictService.findValueByKey(SystemDictKey.NULS_NET_ASSETID));
        String nulsNet=systemDictService.getApiAddressForNULSNET();
        Map result= null;
        String  transactionHash="";
        try {
            result = httpClient.getRpcHttpClient(nulsNet).invoke("transfer",new Object[]{chainId, assetId, address, address, password, SystemDictKey.NULS_NET_AMOUNT, remark}, Map.class);
            transactionHash=(String) result.get("hash");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        return transactionHash;
    }

    public VerifyDataPO getDataForChain(String hash) throws Exception {
        VerifyDataPO verifyDataPO=new VerifyDataPO();
        int chainId=Integer.parseInt(systemDictService.findValueByKey(SystemDictKey.NULS_NET_CHAINID));
        String nulsNet=systemDictService.getApiAddressForNULSNET();
        Map result= null;
        String  data="";
        try {
            result = httpClient.getRpcHttpClient(nulsNet).invoke("getTx",new Object[]{chainId,hash}, Map.class);
            data=(String) result.get("remark");
            verifyDataPO.setHash((String)result.get("hash"));
            verifyDataPO.setRemark((String) result.get("remark"));
            verifyDataPO.setTime((String) result.get("time"));
            verifyDataPO.setBlockHeight((Integer) result.get("blockHeight"));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return verifyDataPO;
    }
}
