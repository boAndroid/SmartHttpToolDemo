package com.smart.httptool.baseinterface;

import com.smart.httptool.httpprotocol.HttpMethod;
import com.smart.httptool.httpprotocol.HttpRequest;
import com.smart.httptool.realhttp.httpurlconnection.HttpUrlConnectionRequestFactory;
import com.smart.httptool.realhttp.okhttp.OkHttpRequestFactory;
import com.smart.httptool.utils.Utils;

/**
 * Created by liubo on 2017/4/15.
 */

public class HttpRequestProvider {

    private static boolean OKHTTP_REQUEST = Utils.isExist("okhttp3.OkHttpClient");

    private HttpRequestFactory mHttpRequestFactory;

    public HttpRequestProvider(){
        if(OKHTTP_REQUEST){
            mHttpRequestFactory = new OkHttpRequestFactory();
        }else{
            mHttpRequestFactory = new HttpUrlConnectionRequestFactory();
        }
    }

    public HttpRequest getHttpRequest(String url, HttpMethod httpMethod) throws Exception {
        return mHttpRequestFactory.createHttpRequest(url, httpMethod);
    }

}
