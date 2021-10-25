package com.letaotao.core;

import com.letaotao.domain.Order;
import com.letaotao.domain.Point;

import java.math.BigInteger;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.checkArgument;

public class EllipticCurveImpl implements EllipticCurve
{

    private final Predicate<Object> notNull;

    private final Order order;

    public EllipticCurveImpl(final Order order)
    {
        this.notNull = obj -> obj != null;
        this.order = order;
    }

    @Override
    public Point add(Point p1, Point p2)
    {
        checkArgument(notEmpty(p1, p2, order));

        if (p1.equals(p2))
        {
            return doubling(p1);
        }

        final BigInteger p = order.getOrder();

        // (y1-y2)*inverse(x1-x2, p)
        final BigInteger slope = inverse(p1.getX().subtract(p2.getX())).multiply(p1.getY().subtract(p2.getY()));

        // pow(slope,2,p)-(x1+x2)
        final BigInteger xSum = slope.pow(2).mod(p).subtract(p1.getX().subtract(p2.getX()));

        // slope*(x1-xsum)-y1
        final BigInteger ySum = slope.multiply(p1.getX().subtract(xSum)).subtract(p1.getY());

        return new Point(xSum.mod(p), ySum.mod(p));
    }

    @Override
    public Point multiply(Point p, BigInteger key)
    {

        Point capitalQ = new Point(BigInteger.ZERO, BigInteger.ZERO);

        for (char c : key.toString(2).toCharArray())
        {
            if (c == '1')
            {
                capitalQ = add(capitalQ, p);
            }
        }

        return doubling(p);
    }

    @Override
    public Point doubling(Point point)
    {
        checkArgument(point != null, "The point to be doubled is empty");

        if (BigInteger.ZERO.equals(point.getY()))
        {
            new IllegalArgumentException("Y not on the curve");
        }

        final BigInteger x = point.getX();

        final BigInteger y = point.getY();

        final BigInteger p = order.getOrder();

        final BigInteger slope = Constants.THREE.multiply(x.pow(2).mod(p)).multiply(inverse(Constants.TWO.multiply(y)));

        final BigInteger xSum = slope.pow(2).mod(p).subtract(Constants.TWO.multiply(x));

        final BigInteger ySum = slope.multiply(x.subtract(xSum)).subtract(y);

        return new Point(xSum.mod(p), ySum.mod(p));
    }

    @Override
    public BigInteger inverse(BigInteger x)
    {

        BigInteger p = order.getOrder();

        BigInteger inv1 = BigInteger.ONE;

        BigInteger inv2 = BigInteger.ZERO;

        BigInteger tmp;

        while ((!p.equals(BigInteger.ONE)) && (!p.equals(BigInteger.ZERO)))
        {

            // inv1, inv2 = inv2, inv1 - inv2 * (x / p)
            tmp = inv1.subtract(inv2.multiply(x.divide(p)));
            inv1 = inv2;
            inv2 = tmp;

            // x, p = p, x % p
            tmp = x.mod(p);
            x = p;
            p = tmp;
        }

        return inv2;
    }

    @Override
    public boolean isOnEllipticCurve(Point point)
    {
        final BigInteger p = order.getOrder();

        final BigInteger x = point.getX();

        final BigInteger y = point.getY();

        final BigInteger left = y.multiply(y).mod(p);

        final BigInteger right = x.multiply(x).multiply(x).add(Constants.b).mod(p);

        return left.equals(right);
    }

    private Boolean notEmpty(Object... objects)
    {
        return Stream.of(objects).allMatch(notNull);
    }
}
