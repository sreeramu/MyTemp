package com.sreeramu.test;

import retrofit2.Call;

public class ApiCalls
{
    public static Call<SearchResult> searchStocksCall(String query)
    {
        return ApiServices.getStocksService().searchStocks(query);
    }
    
    public static Call<String> priceUpdatesCall(String async)
    {
        return ApiServices.getStocksService().priceUpdates(async);
    }
}
