package com.letaotao.util;

import java.util.HashMap;

public class BlockConfig {

    public static HashMap<String, String> configs = new HashMap<>();
    final static String DIRECTION = "DIRECTION";
    final static String UP = "UP";
    // KEY: DIRECTION
    static {
        configs.put(DIRECTION, UP);
    }

    public static boolean isDirectionUp(){
        return configs.get(DIRECTION).equals(UP);
    }
}
