package com.smart.httptool.utils;

/**
 * Created by liubo on 2017/4/15.
 */

public class Utils {
    public static boolean isExist(String className){
        try {
            Class.forName(className);
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
