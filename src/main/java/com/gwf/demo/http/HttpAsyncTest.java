package com.gwf.demo.http;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by gaowenfeng on 2017/8/23.
 */
public class HttpAsyncTest {
    public static void main(String[] argv) {
        CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
        httpclient.start();

        final CountDownLatch latch = new CountDownLatch(1);

        final HttpGet request = new HttpGet("http://192.168.0.187:8080/api/workspace");

        HttpPost httpPost = new HttpPost("http://192.168.0.187:8080/api/workspace");
        String jsonParam = "{\n" +
                "  \"name\": \"myworkspace\",\n" +
                "  \"commands\": [\n" +
                "    {\n" +
                "      \"name\": \"build\",\n" +
                "      \"type\": \"mvn\",\n" +
                "      \"attributes\": {\"goal\":\"Build\",\"previewUrl\":\"\"},\n" +
                "      \"commandLine\": \"mvn clean install\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"environments\": {\"myworkspace\":\n" +
                " \t\t\t{\"recipe\":\n" +
                " \t\t\t\t\t{\"location\":\"eclipse/ubuntu_jdk8\",\"type\":\"dockerimage\"},\n" +
                " \t\t\t \"machines\":\n" +
                " \t\t\t\t\t{\"dev-machine\":\n" +
                " \t\t\t\t\t\t{\"attributes\":{\"memoryLimitBytes\":\"2147483648\"},\n" +
                " \t\t\t\t\t\t \"agents\":[\"org.eclipse.che.exec\",\"org.eclipse.che.terminal\",\"org.eclipse.che.ws-agent\",\"org.eclipse.che.ssh\"],\n" +
                " \t\t\t\t\t\t \"servers\":{}\n" +
                " \t\t\t\t\t\t}\n" +
                " \t\t\t\t\t}\n" +
                " \t\t\t}\n" +
                " \t\t},\n" +
                "  \"defaultEnv\": \"myworkspace\",\n" +
                "  \"projects\": [],\n" +
                "  \"links\": []\n" +
                "}";
        StringEntity entity = new StringEntity(jsonParam,"utf-8");
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);

        System.out.println(" caller thread id is : " + Thread.currentThread().getId());

        httpclient.execute(httpPost, new FutureCallback<HttpResponse>() {

            public void completed(final HttpResponse response) {
                latch.countDown();
                System.out.println(" callback thread id is : " + Thread.currentThread().getId());
                try {
                    String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                    System.out.println(" response content is : " + content);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            public void failed(final Exception ex) {
                latch.countDown();
                System.out.println(request.getRequestLine() + "->" + ex);
                System.out.println(" callback thread id is : " + Thread.currentThread().getId());
            }

            public void cancelled() {
                latch.countDown();
                System.out.println(request.getRequestLine() + " cancelled");
                System.out.println(" callback thread id is : " + Thread.currentThread().getId());
            }

        });
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            httpclient.close();
        } catch (IOException ignore) {

        }
    }


}

