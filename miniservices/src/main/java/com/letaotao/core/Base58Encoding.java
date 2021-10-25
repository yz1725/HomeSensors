package com.letaotao.core;

import com.letaotao.domain.Base58;

import java.math.BigInteger;

import static java.math.BigInteger.ZERO;

//import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Base58Encoding implements Encoding<Base58>
{
    private static final char[] ALPHABET = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz".toCharArray();

    private static final BigInteger BASE = new BigInteger("58");

    @Override
    public Base58 encode(byte[] input)
    {
        final StringBuilder sb = new StringBuilder("");

        BigInteger bi = new BigInteger(1, input);

        int zeros = countLeadingZeros(bi.toByteArray());

        while (bi.compareTo(ZERO) > 0)
        {
            BigInteger[] divideAndRemainder = bi.divideAndRemainder(BASE);

            bi = divideAndRemainder[0];

            BigInteger remainder = divideAndRemainder[1];

            sb.append(ALPHABET[remainder.intValue()]);
        }

        while (zeros > 0)
        {
            sb.append(ALPHABET[0]);
            zeros--;
        }

        return new Base58(sb.reverse().toString().getBytes());
    }

    @Override
    public byte[] decode(Base58 base)
    {
//        throw new NotImplementedException();
        return null;
    }

    private int countLeadingZeros(byte[] number)
    {
        int zeros = 0;
        while (zeros < number.length && number[zeros] == 0)
        {
            ++zeros;
        }
        return zeros;
    }

}
