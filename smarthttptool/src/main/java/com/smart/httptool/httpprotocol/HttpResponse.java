package com.smart.httptool.httpprotocol;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by liubo on 2017/4/15.
 */

public interface HttpResponse extends Header, Closeable{

    HttpStatus getStatus();

    String getStatusMsg();

    InputStream getBody() throws IOException;

    void close();

    long getContentLength();

}
