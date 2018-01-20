package com.sreeramu.strade.converter;

import java.io.IOException;

import okhttp3.Response;

public final class PriceUpdateConverter implements DataConverter
{
    
    private PriceUpdateConverter()
    {
        
    }
    
    private static final PriceUpdateConverter INSTANCE = new PriceUpdateConverter();
    
    public static PriceUpdateConverter getInstance()
    {
        return INSTANCE;
    }
    
    @Override
    public String convert(Response response)
    {
        StringBuilder respBody = new StringBuilder();
        try
        {
            respBody.append(response.body().string().replaceAll("(\\r|\\n|\\t)", ""));
            // System.out.println(respBody);
            respBody = respBody.delete(0, ")]}'{\"PriceUpdates\":[[".length());
            respBody.setLength(respBody.length() - "]]}".length());
            System.out.println(respBody);
            // System.out.println(Arrays.toString(respBody.split(",")));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        /*
         * String matchedData = null; while (m.find()) { matchedData = m.group(1); //System.out.println(matchedData); }
         */
        return respBody.toString();
    }
    
}
