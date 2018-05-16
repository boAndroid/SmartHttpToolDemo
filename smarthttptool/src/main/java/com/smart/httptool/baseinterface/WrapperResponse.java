package com.smart.httptool.baseinterface;

import com.smart.httptool.convert.Convert;

import java.util.List;

/**
 * Created by liubo on 2017/4/15.
 */

public class WrapperResponse extends SmartResponse<String>{

    private SmartResponse mSmartResponse;
    private List<Convert> mConvert;

    public WrapperResponse(SmartResponse smartResponse, List<Convert> convert) {
        mSmartResponse = smartResponse;
        mConvert = convert;
    }

    @Override
    public void success(String data) {

    }

    @Override
    public void fail(String errorMsg) {

    }
}
