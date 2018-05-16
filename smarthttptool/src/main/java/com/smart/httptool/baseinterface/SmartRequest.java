package com.smart.httptool.baseinterface;

import com.smart.httptool.httpprotocol.HttpMethod;

/**
 * Created by liubo on 2017/4/15.
 */

public class SmartRequest {

    private String mUrl;
    private HttpMethod mHttpMethod;
    private byte[] mData;
    private SmartResponse mSmartResponse;
    private String mContentType;

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public HttpMethod getHttpMethod() {
        return mHttpMethod;
    }

    public void setHttpMethod(HttpMethod httpMethod) {
        mHttpMethod = httpMethod;
    }

    public byte[] getData() {
        return mData;
    }

    public void setData(byte[] data) {
        mData = data;
    }

    public SmartResponse getSmartResponse() {
        return mSmartResponse;
    }

    public void setSmartResponse(SmartResponse smartResponse) {
        mSmartResponse = smartResponse;
    }

    public String getContentType() {
        return mContentType;
    }

    public void setContentType(String contentType) {
        mContentType = contentType;
    }
}
