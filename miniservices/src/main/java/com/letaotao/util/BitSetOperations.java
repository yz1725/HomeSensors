package com.letaotao.util;

import java.util.BitSet;
import java.util.stream.IntStream;

public class BitSetOperations {
    public static String convertBitSetToBinaryString(BitSet bitset){
        int nbits = bitset.size();
        final StringBuilder buffer = new StringBuilder(nbits);
        IntStream.range(0, nbits).mapToObj(i -> bitset.get(i) ? '1' : '0').forEach(buffer::append);
        return buffer.toString();
    }
}
