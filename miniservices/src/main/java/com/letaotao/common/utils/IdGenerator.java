package com.letaotao.common.utils;

import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedGenerator;
import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
public class IdGenerator {
    private final static String DEFAULT_HOST_INFO = "0000000000";
    private final static String suffix = getHostInfo();

    private final static TimeBasedGenerator generator = Generators.timeBasedGenerator(EthernetAddress.fromInterface());

    private IdGenerator() {
    }

    public static String generate() {
        return generator.generate() + "-" + suffix;
    }

    private static String getHostInfo() {
        String hostInfo = DEFAULT_HOST_INFO;
        try {
            String canonicalHost = InetAddress.getLocalHost().getCanonicalHostName();
            hostInfo = String.format("%10d", Integer.toUnsignedLong(canonicalHost.hashCode()));
        } catch (UnknownHostException t) {
            LogUtil.error(log, t, "Encounter exception while getting host info");
        }
        return hostInfo;
    }
}
