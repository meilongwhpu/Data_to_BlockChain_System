package io.nuls.data.rpc.impl;

import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.net.URL;

@Service
public class HttpClient {

    private final static Logger logger = LoggerFactory.getLogger(HttpClient.class);

    private JsonRpcHttpClient rpcHttpClient;

    public JsonRpcHttpClient getRpcHttpClient(String url) {
        try{
                rpcHttpClient=new JsonRpcHttpClient(new URL(url));
            rpcHttpClient.setContentType("application/json;charset=UTF-8");
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return rpcHttpClient;
    }

}
