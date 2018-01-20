package com.sreeramu.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;

import com.squareup.moshi.Moshi;

import okhttp3.Authenticator;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class ApiClients
{
    
    private static Retrofit mClient;
    
    public static synchronized Retrofit getApiClient()
    {
        if (mClient == null)
        {
            Moshi moshi = new Moshi.Builder().build();
            
            OkHttpClient client = new OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(new ChuckInterceptor())
                .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort)))
                .proxyAuthenticator(proxyAuthenticator)
                .build();
            
            mClient = new Retrofit.Builder().baseUrl("http://www.google.com")
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .client(client)
                .build();
        }
        
        return mClient;
    }
}
