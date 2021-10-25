package com.letaotao.common.context;

/**
 * the holder for some thread local data
 */
public class ProcessContextHolder {
    private static final ThreadLocal<ProcessContext> CONTEXT_THREAD_LOCAL = ThreadLocal.withInitial(ProcessContext::new);

    /**
     * get the trova context which bind to current thread
     */
    public static ProcessContext get() {
        return CONTEXT_THREAD_LOCAL.get();
    }

    /**
     * bing the trova context to current thread
     */
    public static void set(ProcessContext processContext) {
        CONTEXT_THREAD_LOCAL.set(processContext);
    }

    /**
     * remove the trova context which bind to current thread
     */
    public static void remove() {
        CONTEXT_THREAD_LOCAL.remove();
    }
}

