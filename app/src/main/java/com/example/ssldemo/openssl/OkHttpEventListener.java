package com.example.ssldemo.openssl;

import android.util.Log;

import okhttp3.*;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;

public class OkHttpEventListener extends EventListener {
    private static final String TAG = "OpenSSL";
    @Override
    public void callStart(Call call) {
     Log.d(TAG,"callStart"+ call);
    }

    @Override
    public void dnsStart(Call call, String domainName) {
        Log.d(TAG,"dnsStart"+ domainName);

    }

    @Override
    public void dnsEnd(Call call, String domainName, List<InetAddress> inetAddressList) {

        Log.d(TAG,"dnsEnd"+ inetAddressList);
    }

    @Override
    public void connectStart(Call call, InetSocketAddress inetSocketAddress, Proxy proxy) {

        Log.d(TAG,"hostName"+ inetSocketAddress.getHostName());
        Log.d(TAG,"Address"+ inetSocketAddress.getAddress());
        Log.d(TAG,"Port"+ inetSocketAddress.getPort());
        Log.d(TAG,"proxy"+ proxy);
    }

    @Override
    public void secureConnectStart(Call call) {
        Log.d(TAG,"secureConnectStart");

    }

    @Override
    public void secureConnectEnd(Call call, Handshake handshake) {
        Log.d(TAG,"secureConnectEnd" + handshake.tlsVersion());
    }


    @Override
    public void callEnd(Call call) {

    }

    @Override
    public void callFailed(Call call, IOException ioe) {
        Log.d(TAG,"callFailed"+ ioe.getMessage());

    }
}
