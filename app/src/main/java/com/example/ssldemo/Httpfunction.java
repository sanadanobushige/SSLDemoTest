package com.example.ssldemo;

import android.content.Context;
import android.util.Log;

import com.example.ssldemo.openssl.OkHttpEventListener;
import com.example.ssldemo.openssl.OpenSSLContext;
import com.example.ssldemo.openssl.OpenSSLSocket;
import com.example.ssldemo.openssl.OpenSSLSocketFactory;
import com.example.ssldemo.openssl.OpenSSLTrustManager;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Proxy;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.ConnectionPool;
import okhttp3.ConnectionSpec;
import okhttp3.Dns;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;

public class Httpfunction {
    private static final String TAG = "OpenSSL";
    private OkHttpClient mOkHttpClient;
    private Context mContext;
    public Httpfunction(Context context) {
        mContext = context;
    }
    public OkHttpClient createOkhttpClient() throws NoSuchAlgorithmException, IOException {
        OpenSSLContext openSSLContext = OpenSSLContext.getInstance();
        OpenSSLSocketFactory openSSLSocketFactory = openSSLContext.getOpenSSLSocketFactory();
       // Socket socket=openSSLSocketFactory.createSocket("eee",43);
//        if(openSSLSocketFactory instanceof SSLSocketFactory){
//            Log.d(TAG,"instanceofopenSSLSocketFactory");
//        }
//        Dns customDns = hostname -> {
//            List<InetAddress> addresses = new ArrayList<>();
//            addresses.add(InetAddress.getByName("172.16.17.246:8080"));
//            return addresses;
//        };
        OkHttpClient.Builder httpbuilder = new OkHttpClient.Builder();
        httpbuilder.connectionSpecs(Collections.singletonList(ConnectionSpec.MODERN_TLS));
        httpbuilder.protocols(Collections.singletonList(Protocol.HTTP_1_1));
        httpbuilder.proxy(Proxy.NO_PROXY);
        httpbuilder.sslSocketFactory(openSSLSocketFactory, new OpenSSLTrustManager());
//        httpbuilder.dns(customDns);
        httpbuilder.connectionPool(new ConnectionPool(0,1, TimeUnit.NANOSECONDS));
        httpbuilder.eventListener(new OkHttpEventListener());
        mOkHttpClient = httpbuilder.build();
        return mOkHttpClient;
    }
}
