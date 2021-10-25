package com.letaotao.hashing;

public class RIPEMD160 extends AbstractHashing
{
    public RIPEMD160()
    {
        super(new org.bouncycastle.jcajce.provider.digest.RIPEMD160.Digest());
    }

}
