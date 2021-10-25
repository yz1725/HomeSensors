package com.letaotao.domain;

import java.math.BigInteger;

public class PublicKey
{
    private Point publicKey;

    public PublicKey(Point publicKey)
    {
        this.publicKey = publicKey;
    }

    public Point getPublicKey()
    {
        return publicKey;
    }

    public BigInteger getPublicKeyX()
    {
        return publicKey.getX();
    }

    public BigInteger getPublicKeyY()
    {
        return publicKey.getY();
    }
}
