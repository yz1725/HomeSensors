package com.letaotao.transformations;

import java.util.function.Function;

public class UncompressedFlagTransformation implements Function<byte[], byte[]>
{
    final static byte UNCOMPRESSED_FLAG = 0x04;

    private final PayloadPrefixing payloadPrefixing;

    public UncompressedFlagTransformation(PayloadPrefixing payloadPrefixing)
    {
        this.payloadPrefixing = payloadPrefixing;
    }

    @Override
    public byte[] apply(byte[] data)
    {
        byte[] prefix = new byte[]{UNCOMPRESSED_FLAG};

        return payloadPrefixing.apply(prefix, data);
    }
}
