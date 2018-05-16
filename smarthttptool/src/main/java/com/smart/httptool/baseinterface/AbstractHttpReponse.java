package com.smart.httptool.baseinterface;

import com.smart.httptool.httpprotocol.HttpResponse;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

/**
 * Created by liubo on 2017/4/15.
 */
public abstract class AbstractHttpReponse implements HttpResponse {

    private static final String GZIP = "gzip";
    private InputStream mGzipInputStream;

    @Override
    public void close() {
        if(mGzipInputStream != null){
            try {
                mGzipInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        closeInternal();
    }

    @Override
    public InputStream getBody() throws IOException {
        InputStream body = getBodyInternal();
        if(isGzip()){
            return getBodyGzip(body);
        }
        return body;
    }

    private InputStream getBodyGzip(InputStream body) throws IOException {
        if(mGzipInputStream == null){
            mGzipInputStream = new GZIPInputStream(body);
        }
        return mGzipInputStream;
    }

    private boolean isGzip(){
        String contentEncoding = getHeader().getContentEncoding();
        if(GZIP.equals(contentEncoding)){
            return true;
        }else {
            return false;
        }
    }

    protected abstract InputStream getBodyInternal() throws IOException;

    protected abstract void closeInternal();

}
