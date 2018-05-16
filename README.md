### 1.SmartHttpTool框架背景
SmartHttpTool对底层网络请求库进行封装，上层调用者只需要传地址和参数就可以进行请求。底层网络库兼容OKHttp和HttpUrlConnction网络框架，也可对此进行其他网络框架的兼容扩展。

### 2.使用方法：</br>
--------------
```java
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
```
