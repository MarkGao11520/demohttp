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

    //执行请求的对象
    private static CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();

    /**
     * 执行创建工作空间的请求
     * @param workSpace 请求的对象
     * @param callBack 回调方法
     */
    public static void createWorkSpace(WorkSpace workSpace,CallbackString callBack){
       HttpPost request = new HttpPost(CheURL.CREAATE_WORK_SPACE);  //post请求，需传入请求的url
       String entity = JSON.toJSONString(workSpace);  //将实体转换成json对象
       request.setEntity(parseEntity(entity));
       executePost(request,callBack);
    }

    /**
     * 执行post请求
     * @param request
     * @param callBack
     */
    private static void executePost(HttpPost request,CallbackString callBack){

        httpclient.start();
        httpclient.execute(request,callBack);
        try {
            callBack.latch.await();  //异步请求，防止线程直接结束
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            httpclient.close();
        } catch (IOException ignore) {

        }

    }

    /**
     * 将json对象进行封装
     * @param param json对象
     * @return HttpPost可使用的StringEntity
     */
    private static StringEntity parseEntity(String param){
        StringEntity entity = new StringEntity(param,CheURL.ENCONDING);
        entity.setContentEncoding(CheURL.ENCONDING);
        entity.setContentType(CheURL.CONTENT_TYPE);
        return entity;
    }
}
