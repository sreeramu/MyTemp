package com.sreeramu.test;

public class Search
{
    public String c;
    
    public String m;
    
    public String nf;
    
    public String nq;
    
    public String s;
    
    public String t;
    
    public String x;
    
    @Override
    public String toString()
    {
        return new StringBuilder()
            // .append("c:")
            // .append(c)
            .append("m:")
            .append(m)
            // .append(",nf:")
            // .append(nf)
            .append(",nq:")
            .append(nq)
            // .append(",s:")
            // .append(s)
            .append(",t:")
            .append(t)
            .append(",x:")
            .append(x)
            .append(System.getProperty("line.separator"))
            .toString();
    }
}
