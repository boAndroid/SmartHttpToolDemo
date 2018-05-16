package com.smart.httptool.api;

import com.smart.httptool.convert.Convert;
import com.smart.httptool.convert.JsonConvert;
import com.smart.httptool.httpprotocol.HttpMethod;
import com.smart.httptool.service.HttpDispatcher;
import com.smart.httptool.baseinterface.SmartRequest;
import com.smart.httptool.baseinterface.SmartResponse;
import com.smart.httptool.baseinterface.WrapperResponse;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by liubo on 2017/4/15.
 */

public class SmartApiProvider {

    private static final String ENCODING = "utf-8";
    private static HttpDispatcher sHttpDispatcher = new HttpDispatcher();
    private static final List<Convert> sConverts = new ArrayList<>();

    static {
        sConverts.add(new JsonConvert());
    }

    private static byte[] encodeParam(Map<String, String> value) {
        if(value == null || value.size() == 0){
            return null;
        }
        StringBuffer buffer = new StringBuffer();
        int count = 0;
        try {
            for(Map.Entry<String, String> entry : value.entrySet()){
                    buffer.append(URLEncoder.encode(entry.getKey(), ENCODING)).
                            append("=").
                            append(URLEncoder.encode(entry.getKey(), ENCODING));
                    if(count != value.size() - 1){
                        buffer.append("&");
                    }
                    count++;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return buffer.toString().getBytes();
    }

    public static void testapi(String url, Map<String, String> value, SmartResponse response){
        SmartRequest request = new SmartRequest();
        WrapperResponse wrapperResponse = new WrapperResponse(response, sConverts);
        request.setUrl(url);
        request.setHttpMethod(HttpMethod.POST);
        request.setData(encodeParam(value));
        request.setSmartResponse(wrapperResponse);
        sHttpDispatcher.add(request);
    }


}
