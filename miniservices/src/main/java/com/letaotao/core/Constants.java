package com.letaotao.core;

import com.letaotao.domain.Order;
import com.letaotao.domain.Point;
import org.bouncycastle.util.encoders.Hex;

import java.math.BigInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Constants
{
    public static final int HEX = 16;

    public static final int BINARY = 2;

    public static final BigInteger a = BigInteger.ZERO;

    public static final BigInteger b = new BigInteger("7");

    public static final BigInteger Gx = new BigInteger
            ("79BE667EF9DCBBAC55A06295CE870B07029BFCDB2DCE28D959F2815B16F81798", HEX);

    public static final BigInteger Gy = new BigInteger
            ("483ADA7726A3C4655DA4FBFC0E1108A8FD17B448A68554199C47D08FFB10D4B8", HEX);

    public static final BigInteger p = new BigInteger(1, Hex.decode
            ("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFFC2F"));

    public static final BigInteger TWO = new BigInteger("2");

    public static final BigInteger THREE = new BigInteger("3");

    public static final BigInteger LIMIT = TWO.pow(256).subtract(BigInteger.ONE);

    public static final Point GENERATOR_POINT = new Point(Gx, Gy);

    public static final Order ORDER = new Order(p);

    public static void printConstants()
    {
        final String equation = "Y^2=X^3+AX+B";

        final String generatorPointIn10 = String.format("G(x,y)=(%s,%s)", Gx.toString(10), Gy.toString(10));

        final String generatorPointInHex = String.format("G(x,y)=(%s,%s)", Gx.toString(HEX), Gy.toString(HEX));

        final String alpha = String.format("A=%s", a.toString(10));

        final String beta = String.format("B=%s", b.toString(10));

        final String order = String.format("p=%s", p.toString(10));

        final String constants = Stream.of(equation, generatorPointIn10, generatorPointInHex, alpha, beta, order)
                                       .collect(Collectors.joining("\n"));

        System.out.println(constants);
    }
}
