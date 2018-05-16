package com.smart.httptool.baseinterface;

/**
 * Created by liubo on 2017/4/15.
 */

public abstract class SmartResponse<T> {

    public abstract void success(T data);

    public abstract void fail(String errorMsg);
}
