package com.sreeramu.test;

import java.util.List;

public class PriceUpdateResult
{
    public List<Stock> stockList;
    
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        if (null != stockList && !stockList.isEmpty())
        {
            for (Stock stock : stockList)
            {
                sb.append(stock.toString());
            }
        }
        return sb.toString();
    }
}
