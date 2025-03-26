package com.example.ssldemo;

import android.os.Bundle;
import android.util.Base64;
import android.util.Log;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.example.ssldemo.openssl.OpenSSLContext;

import java.io.IOException;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Dns;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "OpenSSL";
    private OkHttpClient mOkHttpClient;
    private Httpfunction mHttpfunction;

    static {
        System.loadLibrary("native-openssl");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            sendHttpRequest();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        //test();
    }
    private void sendHttpRequest() throws NoSuchAlgorithmException {
        Log.d(TAG, "sendHttpRequest");
        mHttpfunction = new Httpfunction(MainActivity.this);

        try {
            mOkHttpClient = mHttpfunction.createOkhttpClient();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//       Request request = new Request.Builder().url("http://localhost:8080").build();
        Request request = new Request.Builder().url("https://www.google.com").build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.d(TAG, e.toString());
           }
            @Override
           public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Log.d(TAG, response.body().toString());
           }
        });

        Log.d(TAG,"ssl:-----"+mOkHttpClient.sslSocketFactory());
    }


//    private void test() {
//        OpenSSLContext openSSLContext = new OpenSSLContext();
//        long sslCtxPtr = openSSLContext.createNativeSSLContext();
//        Log.d(TAG, "createNativeSSLContext=====" + sslCtxPtr);
//    }
}