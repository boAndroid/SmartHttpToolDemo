package com.smart.httptool.convert;

import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * Created by liubo on 2017/4/15.
 */

public class JsonConvert implements Convert{

    private static final String CONTENT_TYPE = "application/json;charset=UTF-8";

    private Gson mGson = new Gson();

    @Override
    public boolean isCanParse(String contentType) {
        return CONTENT_TYPE.equals(contentType);
    }

    @Override
    public Object parse(String content, Type type) {
        return mGson.fromJson(content, type);
    }
}
