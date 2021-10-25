package com.letaotao.core;

import com.letaotao.domain.Network;

public class NetworkByteFactory
{
    public byte create(final Network network)
    {
        Byte b;

        switch (network)
        {
            case TEST:
                b = new Byte((byte) 0x6F);
                break;
            case MAIN:
                b = new Byte((byte) 0x00);
                break;
            default:
                b = new Byte((byte) 0x00);
        }

        return b;
    }
}
