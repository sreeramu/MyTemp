package com.sreeramu.test;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyTest
{
    public static void main(String[] args)
    {
        ApiCalls.searchStocksCall("infosys").enqueue(new Callback<SearchResult>()
        {
            @Override
            public void onResponse(final Call<SearchResult> call, final Response<SearchResult> response)
            {
                if (response.isSuccessful())
                {
                    System.out.println("---RESP1---");
                    System.out.println(response.body());
                    // onResponseSuccess(response);
                }
                else
                {
                    // onResponseUnsuccessful();
                }
                
            }
            
            @Override
            public void onFailure(final Call<SearchResult> call, final Throwable t)
            {
                // onCallFailure();
            }
        });
        
        ApiCalls.priceUpdatesCall(
            "lang:en,country:in,rmids:/g/1hbvk8ks9|/m/07zln7n|/g/1dv441w5|/g/1dv257df|/g/1dtrwz4n|/g/1dv45__h|/g/1dv1hcwx|/g/1dv4vcmh|/g/1hbvhj0n1|/g/11x19n5ft|/g/1dv4vcmh,_fmt:jspb")
            .enqueue(new Callback<String>()
            {
                
                @Override
                public void onResponse(Call<String> call, Response<String> response)
                {
                    if (response.isSuccessful())
                    {
                        System.out.println("---RESP2---");
                        System.out.println(response.body());
                        // onResponseSuccess(response);
                    }
                    else
                    {
                        // onResponseUnsuccessful();
                    }
                }
                
                @Override
                public void onFailure(Call<String> call, Throwable t)
                {
                    // TODO Auto-generated method stub
                    
                }
            });
    }
}
