package com.sreeramu.strade.converter;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Response;

public final class SearchDataConverter implements DataConverter
{
    private SearchDataConverter()
    {
        
    }
    
    private static final SearchDataConverter INSTANCE = new SearchDataConverter();
    
    public static SearchDataConverter getInstance()
    {
        return INSTANCE;
    }
    
    private Pattern p = Pattern.compile("\\{(.*?)\\}");
    
    @Override
    public String convert(Response response)
    {
        StringBuilder sb = new StringBuilder().append("{\"searchList\":[");
        boolean hasData = false;
        Matcher m = null;
        try
        {
            m = p.matcher(response.body().string());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        String matchedData = null;
        while (m.find())
        {
            matchedData = m.group(1);
            if (matchedData.contains("\"x\""))
            {
                hasData = true;
                sb.append("{").append(matchedData).append("},");
            }
        }
        if (hasData)
        {
            sb.setLength(sb.length() - 1);
        }
        sb.append("]}");
        return sb.toString();
    }
    
}
