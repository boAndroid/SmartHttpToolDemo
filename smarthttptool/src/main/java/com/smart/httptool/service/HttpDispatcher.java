package com.smart.httptool.service;

import com.smart.httptool.baseinterface.HttpRequestProvider;
import com.smart.httptool.baseinterface.HttpRunnable;
import com.smart.httptool.baseinterface.SmartRequest;
import com.smart.httptool.httpprotocol.HttpRequest;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by liubo on 2017/4/15.
 */

public class HttpDispatcher {

    private static final int MAX_REQUEST_SIZE = 10;
    private static final ThreadPoolExecutor sThreadPool = new ThreadPoolExecutor(0, MAX_REQUEST_SIZE, 60, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());

    private Deque<SmartRequest> mRunningRequest = new ArrayDeque<>();
    private Deque<SmartRequest> mCacheRequest = new ArrayDeque<>();
    private HttpRequestProvider mHttpRequestProvider;

    public HttpDispatcher() {
        mHttpRequestProvider = new HttpRequestProvider();
    }

    public void add(SmartRequest request){
        if(mRunningRequest.size() > MAX_REQUEST_SIZE){
            mCacheRequest.add(request);
        }else{
            doHttpRequest(request);
        }
    }

    private void doHttpRequest(SmartRequest request) {
        try {
            HttpRequest httpRequest = mHttpRequestProvider.getHttpRequest(request.getUrl(), request.getHttpMethod());
            sThreadPool.execute(new HttpRunnable(httpRequest, request , this));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void finish(SmartRequest smartRequest) {
        mRunningRequest.remove(smartRequest);
        if(mRunningRequest.size() > MAX_REQUEST_SIZE){
            return;
        }
        if(mCacheRequest.size() == 0){
            return;
        }
        Iterator<SmartRequest> iterator = mCacheRequest.iterator();
        while (iterator.hasNext()){
            SmartRequest next = iterator.next();
            mRunningRequest.add(next);
            iterator.remove();
            doHttpRequest(next);
        }
    }
}
