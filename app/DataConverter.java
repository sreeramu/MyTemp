package com.sreeramu.strade.converter;

import okhttp3.Response;

public interface DataConverter
{
    public String convert(Response response);
}
