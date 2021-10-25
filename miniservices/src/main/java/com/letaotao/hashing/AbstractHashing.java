package com.letaotao.hashing;

import java.security.MessageDigest;

public abstract class AbstractHashing implements Hashing
{
    private final MessageDigest messageDigest;

    public AbstractHashing(final MessageDigest messageDigest)
    {
        this.messageDigest = messageDigest;
    }

    public byte[] get(final byte[] message)
    {
        messageDigest.update(message);
        return messageDigest.digest();
    }

}
