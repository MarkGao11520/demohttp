package com.gwf.demo.http.net;

import lombok.AllArgsConstructor;
import org.apache.http.HttpResponse;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by gaowenfeng on 2017/8/23.
 */
@AllArgsConstructor
public class CallbackString implements FutureCallback<HttpResponse>{
    private INetResult<String> netResult;
    final public CountDownLatch latch = new CountDownLatch(1);

    public void completed(HttpResponse response) {
        latch.countDown();
        String content = "";
        try {
            content = EntityUtils.toString(response.getEntity(), CheURL.ENCONDING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        netResult.getNetData(content);
    }

    public void failed(Exception e) {
        latch.countDown();
        e.printStackTrace();
    }

    public void cancelled() {
        latch.countDown();
    }
}
