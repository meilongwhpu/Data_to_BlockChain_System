package io.nuls.data.rpc.impl;

import io.nuls.data.rpc.ChainBlockService;
import io.nuls.data.service.SystemDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChainBlockForNulsMainService implements ChainBlockService {
    @Autowired
    private HttpClient httpClient;

    @Autowired
    private SystemDictService systemDictService;

    @Override
    public String saveData(String address, String password,String remark) throws Exception {
        String nulsMain= systemDictService.getApiAddressForNULSMAIN();
        return "";
    }

}
