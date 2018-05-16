package com.smart.httptool.realhttp.okhttp;

import com.smart.httptool.baseinterface.AbstractHttpRequest;
import com.smart.httptool.httpprotocol.HttpHeader;
import com.smart.httptool.httpprotocol.HttpMethod;
import com.smart.httptool.httpprotocol.HttpResponse;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by liubo on 2017/4/15.
 */

public class OkHttpRequest extends AbstractHttpRequest {

    private OkHttpClient mClient;
    private HttpMethod mHttpMethod;
    private String mUrl;

    public OkHttpRequest(OkHttpClient client, HttpMethod httpMethod, String url) {
        mClient = client;
        mHttpMethod = httpMethod;
        mUrl = url;
    }

    @Override
    public HttpMethod getMethod() {
        return mHttpMethod;
    }

    @Override
    public URI getUri() {
        return URI.create(mUrl);
    }

    @Override
    protected HttpResponse executeInternal(HttpHeader httpHeader, byte[] body) throws IOException {
        boolean isBody = mHttpMethod == HttpMethod.POST;
        RequestBody requestBody = null;
        if(isBody){
            requestBody = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"), body);
        }
        Request.Builder builder = new Request.Builder().url(mUrl).method(mHttpMethod.name(), requestBody);
        for(Map.Entry<String, String> entry : httpHeader.entrySet()){
            builder.addHeader(entry.getKey(), entry.getValue());
        }
        Response response = mClient.newCall(builder.build()).execute();
        return new OkHttpResponse(response);
    }
}
