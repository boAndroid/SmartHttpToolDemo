package com.smart.httptool.realhttp.httpurlconnection;

import com.smart.httptool.baseinterface.AbstractHttpRequest;
import com.smart.httptool.httpprotocol.HttpHeader;
import com.smart.httptool.httpprotocol.HttpMethod;
import com.smart.httptool.httpprotocol.HttpResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.Map;


/**
 * Created by liubo on 2017/4/15.
 */

public class HttpUrlConnectionRequest extends AbstractHttpRequest {

    private HttpURLConnection mConnection;

    private String mUrl;

    private HttpMethod mMethod;

    public HttpUrlConnectionRequest(HttpURLConnection connection, HttpMethod method, String url) {
        this.mConnection = connection;
        this.mUrl = url;
        this.mMethod = method;
    }

    @Override
    protected HttpResponse executeInternal(HttpHeader header, byte[] data) throws IOException {
        for (Map.Entry<String, String> entry : header.entrySet()) {
            mConnection.addRequestProperty(entry.getKey(), entry.getValue());
        }
        mConnection.setDoOutput(true);
        mConnection.setDoInput(true);
        mConnection.setRequestMethod(mMethod.name());
        mConnection.connect();
        if (data != null && data.length > 0) {
            OutputStream out = mConnection.getOutputStream();
            out.write(data,0,data.length);
            out.close();
        }
        HttpUrlConnectionResponse response = new HttpUrlConnectionResponse(mConnection);
        return response;
    }

    @Override
    public HttpMethod getMethod() {
        return mMethod;
    }

    @Override
    public URI getUri() {
        return URI.create(mUrl);
    }

}
