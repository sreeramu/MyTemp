package com.sreeramu.test;

public class Stock
{
    private String stockCode;
    
    private String stockSymbol;
    
    private String stockUpdateTime;
    
    private String stockCurrentValue;
    
    private String stockChangeValue;
    
    private String stockChangePercent;
    
    private String stockChangeIndex;
    
    public void processData(String content)
    {
        char[] chars = content.toCharArray();
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        int startCount = -2;
        for (int i = 0; i < chars.length; i++)
        {
            if (chars[i] == '[')
            {
                if (startCount < -1)
                {
                    startCount = -1;
                }
                else if (startCount < 0)
                {
                    startCount = 0;
                }
                if (startCount >= 0)
                {
                    startCount += 1;
                }
            }
            else if (chars[i] == ']')
            {
                startCount -= 1;
            }
            
            if (startCount < 0)
            {
                sb1.append(chars[i]);
            }
            else
            {
                sb2.append(chars[i]);
            }
            
            if (startCount == 0)
            {
                startCount = -2;
            }
        }
        
        if (sb1.length() > 0 && sb2.length() > 0)
        {
            sb1.deleteCharAt(0);
            sb1.setLength(sb1.length() - 1);
            sb2.deleteCharAt(0);
            sb2.setLength(sb1.length() - 1);
            
            String[] stockData = sb1.toString().split("\"");
            stockCode = stockData[1];
            stockSymbol = stockData[3];
            stockUpdateTime = stockData[5];
            stockData = sb2.toString().split("\"");
            stockCurrentValue = stockData[1].replaceAll(",", "");
            stockChangeIndex = stockData[6].split(",")[1].equals("1") ? "" : "-";
            stockChangeValue = stockChangeIndex + stockData[3].replaceAll(",", "");
            stockChangePercent = stockChangeIndex + stockData[5];
        }
    }
    
    public String toJson()
    {
        return new StringBuilder().append("{")
            .append("\"stockCode\":\"")
            .append(stockCode)
            .append("\",\"stockSymbol\":\"")
            .append(stockSymbol)
            .append("\",\"stockUpdateTime\":\"")
            .append(stockUpdateTime)
            .append("\",\"stockCurrentValue\":\"")
            .append(stockCurrentValue)
            .append("\",\"stockChangeValue\":\"")
            .append(stockChangeValue)
            .append("\",\"stockChangePercent\":\"")
            .append(stockChangePercent)
            .append("\"}")
            .toString();
    }
    
    @Override
    public String toString()
    {
        return new StringBuilder().append("{")
            .append("\"stockCode\":\"")
            .append(stockCode)
            .append("\",\"stockSymbol\":\"")
            .append(stockSymbol)
            .append("\",\"stockUpdateTime\":\"")
            .append(stockUpdateTime)
            .append("\",\"stockCurrentValue\":\"")
            .append(stockCurrentValue)
            .append("\",\"stockChangeValue\":\"")
            .append(stockChangeValue)
            .append("\",\"stockChangePercent\":\"")
            .append(stockChangePercent)
            .append("\"}")
            .append(System.getProperty("line.separator"))
            .toString();
    }
}
