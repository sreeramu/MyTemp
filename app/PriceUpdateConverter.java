package com.sreeramu.strade.converter;

import java.io.IOException;

import com.sreeramu.test.Stock;

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
            return parseData(respBody.toString());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return respBody.toString();
    }
    
    private static String parseData(String content)
    {
        StringBuilder resultSb = new StringBuilder();
        resultSb.append("{\"stockList\":[");
        char[] chars = content.toCharArray();
        int startCount = -1;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.length; i++)
        {
            if (chars[i] == '[')
            {
                if (startCount < 0)
                {
                    startCount = 0;
                }
                startCount += 1;
            }
            else if (chars[i] == ']')
            {
                startCount -= 1;
            }
            if (startCount >= 0)
            {
                sb.append(chars[i]);
            }
            if (startCount == 0)
            {
                startCount = -1;
                Stock ss = new Stock();
                ss.processData(sb.toString());
                resultSb.append(ss.toJson()).append(",");
                sb.setLength(0);
            }
        }
        if (resultSb.length() > 1)
        {
            resultSb.setLength(resultSb.length() - 1);
        }
        resultSb.append("]}");
        return resultSb.toString();
    }
    
}
