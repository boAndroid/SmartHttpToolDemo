package com.smart.httptool.baseinterface;

import com.smart.httptool.httpprotocol.HttpHeader;
import com.smart.httptool.httpprotocol.HttpRequest;
import com.smart.httptool.httpprotocol.HttpResponse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.ZipOutputStream;

/**
 * Created by liubo on 2017/4/15.
 */

public abstract class AbstractHttpRequest implements HttpRequest {

    private static final String GZIP = "gzip";
    private com.smart.httptool.httpprotocol.HttpHeader mHttpHeader = new com.smart.httptool.httpprotocol.HttpHeader();
    private ZipOutputStream mZipOutputStream;
    private ByteArrayOutputStream mByteArray = new ByteArrayOutputStream();

    @Override
    public HttpHeader getHeader() {
        return mHttpHeader;
    }

    @Override
    public OutputStream getBody() {
        OutputStream body = getBodyOutputStream();
        if(isGzip()){
            return getGzipOutStream(body);
        }
        return body;
    }

    private OutputStream getBodyOutputStream() {
        return mByteArray;
    }

    private OutputStream getGzipOutStream(OutputStream body) {
        if(mZipOutputStream == null){
            mZipOutputStream = new ZipOutputStream(body);
        }
        return mZipOutputStream;
    }

    private boolean isGzip(){
        String contentEncoding = getHeader().getContentEncoding();
        if(GZIP.equals(contentEncoding)){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public HttpResponse execute() throws IOException{
        if(mZipOutputStream != null){
            mZipOutputStream.close();
        }
        HttpResponse response = executeInternal(mHttpHeader);
        return response;
    }

    private HttpResponse executeInternal(com.smart.httptool.httpprotocol.HttpHeader httpHeader) throws IOException {
        byte[] data = mByteArray.toByteArray();
        return executeInternal(httpHeader, data);
    }

    protected abstract HttpResponse executeInternal(com.smart.httptool.httpprotocol.HttpHeader httpHeader, byte[] data) throws IOException;

}
