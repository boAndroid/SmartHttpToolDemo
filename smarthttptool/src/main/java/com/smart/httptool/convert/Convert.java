package com.smart.httptool.convert;

import java.lang.reflect.Type;

/**
 * Created by liubo on 2017/4/15.
 */
public interface Convert {

    boolean isCanParse(String contentType);

    Object parse(String content, Type type);
}
