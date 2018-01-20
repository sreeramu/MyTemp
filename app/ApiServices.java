package com.sreeramu.test;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class ApiServices
{
    
    private static StocksService mStocksService;
    
    public static synchronized StocksService getStocksService()
    {
        if (mStocksService == null)
        {
            mStocksService = ApiClients.getApiClient().create(StocksService.class);
        }
        
        return mStocksService;
    }
    
    public interface StocksService
    {
        @GET("/complete/search?client=finance-immersive")
        Call<SearchResult> searchStocks(@Query("q") String query);
        
        @GET("/async/finance_price_updates")
        Call<String> priceUpdates(@Query("async") String async);
    }
}
