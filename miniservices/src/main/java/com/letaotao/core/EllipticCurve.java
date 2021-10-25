package com.letaotao.core;

import com.letaotao.domain.Point;

import java.math.BigInteger;

public interface EllipticCurve {
    Point add(Point p1, Point p2);

    Point multiply(Point p, BigInteger key);

    Point doubling(Point p);

    BigInteger inverse(BigInteger x);

    boolean isOnEllipticCurve(Point p);
}
