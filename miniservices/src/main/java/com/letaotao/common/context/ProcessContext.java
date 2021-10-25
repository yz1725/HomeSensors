package com.letaotao.common.context;

import java.util.HashMap;
import java.util.Map;
import lombok.Data;

/**
 * Process context
 */
@Data
public class ProcessContext {

    private String traceId;

    private final Map<String, Object> data = new HashMap();

    /**
     * Instantiates a new Process context.
     */
    public ProcessContext() {
    }

    /**
     * Get t.
     *
     * @param <T> the type parameter
     * @param key the key
     * @return the t
     */
    public <T> T get(String key) {
        return (T) this.data.get(key);
    }

    /**
     * Put t.
     *
     * @param <T>   the type parameter
     * @param key   the key
     * @param value the value
     * @return the t
     */
    public <T> T put(String key, T value) {
        return (T) this.data.put(key, value);
    }
}
