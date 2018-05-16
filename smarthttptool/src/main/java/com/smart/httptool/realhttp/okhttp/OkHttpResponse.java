package com.smart.httptool.realhttp.okhttp;

import com.smart.httptool.baseinterface.AbstractHttpReponse;
import com.smart.httptool.httpprotocol.HttpHeader;
import com.smart.httptool.httpprotocol.HttpStatus;

import java.io.InputStream;

import okhttp3.Response;

/**
 * Created by liubo on 2017/4/15.
 */

public class OkHttpResponse extends AbstractHttpReponse {

    private Response mResponse;
    private HttpHeader mHeader;

    public OkHttpResponse(Response response) {
        this.mResponse = response;
    }

    @Override
    public HttpHeader getHeader() {
        if(mHeader == null){
            mHeader = new HttpHeader();
        }
        for(String name : mResponse.headers().names()){
            mHeader.set(name, mResponse.headers().get(name));
        }
        return mHeader;
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.getValue(mResponse.code());
    }

    @Override
    public String getStatusMsg() {
        return mResponse.message();
    }

    @Override
    public long getContentLength() {
        return mResponse.body().contentLength();
    }

    @Override
    protected InputStream getBodyInternal() {
        return mResponse.body().byteStream();
    }

    @Override
    protected void closeInternal() {
        mResponse.body().close();
    }

}
