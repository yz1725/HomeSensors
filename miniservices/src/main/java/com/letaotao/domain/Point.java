package com.letaotao.domain;

import java.math.BigInteger;

public class Point
{
    private final BigInteger x;

    private final BigInteger y;

    public Point(BigInteger x, BigInteger y)
    {
        this.x = x;
        this.y = y;
    }

    public BigInteger getX()
    {
        return x;
    }

    public BigInteger getY()
    {
        return y;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof Point))
        {
            return false;
        }

        Point point = (Point) o;

        if (!x.equals(point.x))
        {
            return false;
        }
        if (!y.equals(point.y))
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = x.hashCode();
        result = 31 * result + y.hashCode();
        return result;
    }

    @Override
    public String toString()
    {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
