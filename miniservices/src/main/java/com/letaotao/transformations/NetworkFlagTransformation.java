package com.letaotao.transformations;

import com.letaotao.core.NetworkByteFactory;
import com.letaotao.domain.Network;

import java.util.function.Function;

public class NetworkFlagTransformation implements Function<byte[], byte[]>
{
    private Network network;

    private NetworkByteFactory networkByteFactory;

    private PayloadPrefixing payloadPrefixing;

    public NetworkFlagTransformation(final Network network, final PayloadPrefixing payloadPrefixing)
    {
        this.network = network;
        this.payloadPrefixing = payloadPrefixing;
        this.networkByteFactory = new NetworkByteFactory();
    }

    @Override
    public byte[] apply(byte[] data)
    {
        byte prefix = networkByteFactory.create(network);

        return payloadPrefixing.apply(new byte[]{prefix}, data);
    }
}
