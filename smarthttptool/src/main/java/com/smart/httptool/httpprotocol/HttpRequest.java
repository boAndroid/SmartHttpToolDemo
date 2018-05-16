package com.smart.httptool.httpprotocol;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

/**
 * Created by liubo on 2017/4/15.
 */

public interface HttpRequest extends Header{

    HttpMethod getMethod();

    URI getUri();

    OutputStream getBody();

    HttpResponse execute() throws IOException;
}
