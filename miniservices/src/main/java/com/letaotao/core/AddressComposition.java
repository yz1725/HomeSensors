package com.letaotao.core;

import com.letaotao.domain.Network;
import com.letaotao.hashing.Hashing;
import com.letaotao.hashing.RIPEMD160;
import com.letaotao.hashing.SHA256;
import com.letaotao.transformations.HashingTransformation;
import com.letaotao.transformations.NetworkFlagTransformation;
import com.letaotao.transformations.PayloadPrefixing;
import com.letaotao.transformations.UncompressedFlagTransformation;
import com.google.common.primitives.Bytes;

import java.util.Arrays;
import java.util.function.Function;

public class AddressComposition implements Function<byte[], byte[]>
{

    private final Hashing sha256, ripemd160;

    private final PayloadPrefixing payloadPrefixing;

    private final Function<byte[], byte[]> flagPublicKeyAsUncompressed, toSHA256, toRIPEMD160, addNetworkBytes;

    public AddressComposition()
    {
        this.sha256 = new SHA256();
        this.ripemd160 = new RIPEMD160();
        this.payloadPrefixing = new PayloadPrefixing();
        this.flagPublicKeyAsUncompressed = new UncompressedFlagTransformation(payloadPrefixing);
        this.toSHA256 = new HashingTransformation(sha256);
        this.toRIPEMD160 = new HashingTransformation(ripemd160);
        this.addNetworkBytes = new NetworkFlagTransformation(Network.MAIN, payloadPrefixing);
    }

    @Override
    public byte[] apply(byte[] publicKey)
    {
        byte[] address =
                flagPublicKeyAsUncompressed
                        .andThen(toSHA256)
                        .andThen(toRIPEMD160)
                        .andThen(addNetworkBytes)
                        .apply(publicKey);

        return Bytes.concat(address, generateCheckSum(address));
    }

    private byte[] generateCheckSum(byte[] data)
    {
        return extractChecksum(toSHA256.andThen(toSHA256).apply(data));
    }

    private byte[] extractChecksum(byte[] data)
    {
        return Arrays.copyOfRange(data, 0, 4);
    }
}
