package com.gwf.demo.http.net;

import com.alibaba.fastjson.JSON;
import com.gwf.demo.http.dataobject.WorkSpace;
import org.apache.http.HttpRequest;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by gaowenfeng on 2017/8/23.
 */
public class HttpClient {

    private static final String CONTENT_TYPE = "application/json";
    private static CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();

    public static void createWorkSpace(WorkSpace workSpace,CallbackString callBack){
       HttpPost request = new HttpPost(CheURL.CREAATE_WORK_SPACE);
       String entity = JSON.toJSONString(workSpace);
       request.setEntity(parseEntity(entity));
       executePost(request,callBack);
    }

    private static void executePost(HttpPost request,CallbackString callBack){
        httpclient.start();
        httpclient.execute(request,callBack);
        try {
            callBack.latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            httpclient.close();
        } catch (IOException ignore) {

        }

    }

    private static StringEntity parseEntity(String param){
        StringEntity entity = new StringEntity(param,CheURL.ENCONDING);
        entity.setContentEncoding(CheURL.ENCONDING);
        entity.setContentType(CONTENT_TYPE);
        return entity;
    }
}
