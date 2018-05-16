package com.smart.httptool.baseinterface;

import com.smart.httptool.httpprotocol.HttpRequest;
import com.smart.httptool.httpprotocol.HttpResponse;
import com.smart.httptool.service.HttpDispatcher;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by liubo on 2017/4/15.
 */

public class HttpRunnable implements Runnable{

    private HttpRequest mHttpRequest;
    private SmartRequest mSmartRequest;
    private HttpDispatcher mHttpDispatcher;

    public HttpRunnable(HttpRequest httpRequest, SmartRequest smartRequest, HttpDispatcher httpDispatcher) {
        mHttpRequest = httpRequest;
        mSmartRequest = smartRequest;
        mHttpDispatcher = httpDispatcher;
    }

    @Override
    public void run() {
        try {
            mHttpRequest.getBody().write(mSmartRequest.getData());
            HttpResponse response = mHttpRequest.execute();
            String contentType = response.getHeader().getContentType();
            mSmartRequest.setContentType(contentType);
            if(response.getStatus().isSuccess()){
                if(mSmartRequest.getSmartResponse() != null){
                    mSmartRequest.getSmartResponse().success(new String(getData(response)));
                }
            }else {
                mSmartRequest.getSmartResponse().fail(response.getStatusMsg());
            }
        } catch (IOException e) {
            mSmartRequest.getSmartResponse().fail("请求异常");
            e.printStackTrace();
        }finally {
            mHttpDispatcher.finish(mSmartRequest);
        }
    }

    private byte[] getData(HttpResponse response) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream((int)response.getContentLength());
        int len;
        byte[] data = new byte[512];
        try{
            while((len = response.getBody().read(data)) != -1){
                outputStream.write(data, 0, len);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return outputStream.toByteArray();
    }
}


