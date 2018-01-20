package com.sreeramu.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.sreeramu.strade.converter.DataConverter;
import com.sreeramu.strade.converter.PriceUpdateConverter;
import com.sreeramu.strade.converter.SearchDataConverter;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class ChuckInterceptor implements Interceptor
{
    
    Map<String, DataConverter> allDataConverterList = new HashMap<String, DataConverter>();
    
    public ChuckInterceptor()
    {
        allDataConverterList.put("/complete/search", SearchDataConverter.getInstance());
        allDataConverterList.put("/async/finance_price_updates", PriceUpdateConverter.getInstance());
    }
    
    @Override
    public Response intercept(Chain chain)
        throws IOException
    {
        Request request = chain.request();
        String urlPath = request.url().url().getPath();
        System.out.println(request.url().toString());
        System.out.println(request.url().url().getPath());
        Response response;
        try
        {
            response = chain.proceed(request);
        }
        catch (Exception e)
        {
            throw e;
        }
        if (allDataConverterList.containsKey(urlPath))
        {
            String convertedBody = allDataConverterList.get(urlPath).convert(response);
            MediaType contentType = response.body().contentType();
            ResponseBody body = ResponseBody.create(contentType, convertedBody);
            return response.newBuilder().body(body).build();
        }
        return response;
    }
    
}
