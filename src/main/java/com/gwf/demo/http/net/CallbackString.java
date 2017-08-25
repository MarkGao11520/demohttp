package com.gwf.demo.http.net;

import lombok.AllArgsConstructor;
import org.apache.http.HttpResponse;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by gaowenfeng on 2017/8/23.
 * 回调类结果封装
 * 实现FutureCallback接口
 */
@AllArgsConstructor
public class CallbackString implements FutureCallback<HttpResponse>{

    /**
     * 请求成功时对返回结果的处理，须有外部注入
     */
    private INetResult<String> netResult;

    final public CountDownLatch latch = new CountDownLatch(1);

    /**
     * 成功返回时的回调方法
     * @param response
     */
    public void completed(HttpResponse response) {
        latch.countDown();  //收到返回信息以后结束回调方法
        String content = "";
        try {
            content = EntityUtils.toString(response.getEntity(), CheURL.ENCONDING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        netResult.getNetData(content);
    }

    /**
     * 失败时候的回调方法
     * @param e
     */
    public void failed(Exception e) {
        latch.countDown();
        e.printStackTrace();
    }

    /**
     * 取消时候的回调方法
     */
    public void cancelled() {
        latch.countDown();
    }
}
