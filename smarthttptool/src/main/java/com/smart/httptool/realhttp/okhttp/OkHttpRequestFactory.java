package com.smart.httptool.realhttp.okhttp;

import com.smart.httptool.baseinterface.HttpRequestFactory;
import com.smart.httptool.httpprotocol.HttpMethod;
import com.smart.httptool.httpprotocol.HttpRequest;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by liubo on 2017/4/15.
 */

public class OkHttpRequestFactory implements HttpRequestFactory {

    private OkHttpClient mClient;

    public OkHttpRequestFactory() {
        mClient = new OkHttpClient();
    }

    public void setReadTimeOut(int readTimeOut){
        this.mClient = mClient.newBuilder().readTimeout(readTimeOut, TimeUnit.MILLISECONDS).build();
    }

    public void setWriteTimeOut(int writeTimeOut){
        this.mClient = mClient.newBuilder().writeTimeout(writeTimeOut, TimeUnit.MILLISECONDS).build();
    }

    public void setConnectionTimeOut(int connectionTimeOut){
        this.mClient = mClient.newBuilder().connectTimeout(connectionTimeOut, TimeUnit.MILLISECONDS).build();
    }

    @Override
    public HttpRequest createHttpRequest(String url, HttpMethod method) {
        return new OkHttpRequest(mClient, method, url);
    }
}
