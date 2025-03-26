package com.example.ssldemo.openssl;

import android.util.Log;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContextSpi;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSessionContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;


public class OpenSSLContext extends SSLContextSpi {
    private static final String TAG = "OpenSSL";
    private long sslCtxPtr;
    private  OpenSSLSocketFactory openSSLSocketFactory;
    private native long nativeCreateSSLContext();
    //private native void nativeFreeSSLContext(long ctx);
    private OpenSSLContext() {
        sslCtxPtr = nativeCreateSSLContext();
        openSSLSocketFactory = new OpenSSLSocketFactory(sslCtxPtr);
        Log.d(TAG,"OpenSSLContext");
    }

    @Override
    protected void engineInit(KeyManager[] keyManagers, TrustManager[] trustManagers, SecureRandom secureRandom) throws KeyManagementException {
        Log.d(TAG,"engineInit");
    }

    @Override
    protected SSLSocketFactory engineGetSocketFactory() {
        Log.d(TAG,"engineGetSocketFactory");
        return null;
    }

    @Override
    protected SSLServerSocketFactory engineGetServerSocketFactory() {
        Log.d(TAG,"engineGetServerSocketFactory");
        return null;
    }

    @Override
    protected SSLEngine engineCreateSSLEngine() {
        Log.d(TAG,"engineCreateSSLEngine");
        return null;
    }

    @Override
    protected SSLEngine engineCreateSSLEngine(String s, int i) {
        Log.d(TAG,"engineCreateSSLEngine");
        return null;
    }

    @Override
    protected SSLSessionContext engineGetServerSessionContext() {
        Log.d(TAG,"engineGetServerSessionContext");
        return null;
    }

    @Override
    protected SSLSessionContext engineGetClientSessionContext() {
        Log.d(TAG,"engineGetClientSessionContext");
        return null;
    }

    public static OpenSSLContext getInstance()
            throws NoSuchAlgorithmException {
        Log.d(TAG,"getInstance");
        return new OpenSSLContext();
    }
//    @Override
//    protected void finalize() throws Throwable {
//        nativeFreeSSLContext(nativeSSLContext);
//        super.finalize();
//    }
    public final OpenSSLSocketFactory getOpenSSLSocketFactory() {
        Log.d(TAG,"getOpenSSLSocketFactory");
        return openSSLSocketFactory;
    }

}
