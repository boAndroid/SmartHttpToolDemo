package com.httpdemo.smarthttptooldemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.smart.httptool.api.SmartApiProvider;
import com.smart.httptool.baseinterface.SmartResponse;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.test_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                test();
            }
        });
    }

    private void test(){
        Map<String, String> map = new HashMap<>();
        map.put("username", "lili");
        map.put("userage", "15");
        SmartApiProvider.testapi("http://192.168.1.12:8080/web/hello", map, new SmartResponse<Person>() {
            @Override
            public void success(Person data) {
                Log.i("tag", data.toString());
            }

            @Override
            public void fail(String errorMsg) {
                Log.i("tag", errorMsg);
            }
        });
    }
}
