package com.letaotao.domain;

public class Base58
{
    private final byte[] data;

    public Base58(byte[] data)
    {
        this.data = data;
    }

    public byte[] get()
    {
        return data;
    }
}
