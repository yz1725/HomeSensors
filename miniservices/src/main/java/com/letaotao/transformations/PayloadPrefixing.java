package com.letaotao.transformations;

public class PayloadPrefixing
{

    public PayloadPrefixing() {}

    public byte[] apply(byte[] prefix, byte[] data)
    {
        byte[] newData = new byte[prefix.length + data.length];

        addPrefix(data, prefix, newData);

        return newData;
    }

    private void addPrefix(byte[] data, byte[] prefix, byte[] newData)
    {
        System.arraycopy(prefix, 0, newData, 0, prefix.length);
        System.arraycopy(data, 0, newData, prefix.length, data.length);
    }
}
