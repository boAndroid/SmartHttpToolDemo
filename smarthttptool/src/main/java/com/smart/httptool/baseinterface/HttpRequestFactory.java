package com.smart.httptool.baseinterface;

import com.smart.httptool.httpprotocol.HttpMethod;
import com.smart.httptool.httpprotocol.HttpRequest;

/**
 * Created by liubo on 2017/4/15.
 */

public interface HttpRequestFactory {
    HttpRequest createHttpRequest(String url, HttpMethod method) throws Exception;
}
