package com.smart.httptool.httpprotocol;

import java.util.Map;

/**
 * Created by liubo on 2017/4/15.
 */

public interface NameValueMap<K, V> extends Map<K, V> {

    String get(String name);

    void set(String name, String value);

}
