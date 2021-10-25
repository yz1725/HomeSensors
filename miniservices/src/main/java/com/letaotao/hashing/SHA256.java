package com.letaotao.hashing;

public class SHA256 extends AbstractHashing
{
    public SHA256()
    {
        super(new org.bouncycastle.jcajce.provider.digest.SHA256.Digest());
    }

}
