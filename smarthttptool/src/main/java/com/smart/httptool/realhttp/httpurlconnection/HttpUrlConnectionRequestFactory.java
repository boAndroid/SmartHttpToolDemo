package com.smart.httptool.realhttp.httpurlconnection;

import com.smart.httptool.baseinterface.HttpRequestFactory;
import com.smart.httptool.httpprotocol.HttpMethod;
import com.smart.httptool.httpprotocol.HttpRequest;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by liubo on 2017/4/15.
 */

public class HttpUrlConnectionRequestFactory implements HttpRequestFactory {

    private HttpURLConnection mConnection;

    public HttpUrlConnectionRequestFactory() {

    }

    public void setReadTimeOut(int readTimeOut) {
        mConnection.setReadTimeout(readTimeOut);
    }

    public void setConnectionTimeOut(int connectionTimeOut) {
        mConnection.setConnectTimeout(connectionTimeOut);
    }

    @Override
    public HttpRequest createHttpRequest(String url, HttpMethod method) throws Exception {
        URL mUrl = new URL(url);
        mConnection = (HttpURLConnection) mUrl.openConnection();
        return new HttpUrlConnectionRequest(mConnection, method, url);
    }
}
