package com.sreeramu.test;

import java.util.List;

public class SearchResult
{
    public List<Search> searchList;
    
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        if (null != searchList && !searchList.isEmpty())
        {
            for (Search singleResult : searchList)
            {
                sb.append(singleResult.toString());
            }
        }
        return sb.toString();
    }
}
