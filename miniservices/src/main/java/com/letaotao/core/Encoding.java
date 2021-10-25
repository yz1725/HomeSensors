package com.letaotao.core;

public interface Encoding<T>
{
    public T encode(byte[] input);

    public byte[] decode(T base);
}
