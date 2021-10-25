package com.letaotao.common.utils;

import com.letaotao.common.context.ProcessContextHolder;
import org.slf4j.Logger;
import org.springframework.util.StringUtils;

/**
 * The type Log util.
 */
public class LogUtil {
    private static final String SEP = ",";

    private static final char LEFT_TAG = '[';

    private static final char RIGHT_TAG = ']';

    /**
     * Info.
     *
     * @param logger  the logger
     * @param objects the objects
     */
    public static void info(Logger logger, Object... objects) {
        if (logger.isInfoEnabled()) {
            logger.info(getLogString(objects));
        }
    }

    /**
     * Info.
     *
     * @param logger  the logger
     * @param t       the t
     * @param objects the objects
     */
    public static void info(Logger logger, Throwable t, Object... objects) {
        if (logger.isInfoEnabled()) {
            logger.info(getLogString(objects), t);
        }
    }

    /**
     * Warn.
     *
     * @param logger  the logger
     * @param objects the objects
     */
    public static void warn(Logger logger, Object... objects) {
        if (logger.isWarnEnabled()) {
            logger.warn(getLogString(objects));
        }
    }

    /**
     * Warn.
     *
     * @param logger  the logger
     * @param t       the t
     * @param objects the objects
     */
    public static void warn(Logger logger, Throwable t, Object... objects) {
        if (logger.isWarnEnabled()) {
            logger.warn(getLogString(objects), t);
        }
    }

    /**
     * Error.
     *
     * @param logger  the logger
     * @param objects the objects
     */
    public static void error(Logger logger, Object... objects) {
        if (logger.isErrorEnabled()) {
            logger.error(getLogString(objects));
        }
    }

    /**
     * Error.
     *
     * @param logger  the logger
     * @param t       the t
     * @param objects the objects
     */
    public static void error(Logger logger, Throwable t, Object... objects) {
        if (logger.isErrorEnabled()) {
            logger.error(getLogString(objects), t);
        }
    }

    /**
     * Debug.
     *
     * @param logger  the logger
     * @param objects the objects
     */
    public static void debug(Logger logger, Object... objects) {
        if (logger.isDebugEnabled()) {
            logger.debug(getLogString(objects));
        }
    }

    /**
     * Debug.
     *
     * @param logger  the logger
     * @param t       the t
     * @param objects the objects
     */
    public static void debug(Logger logger, Throwable t, Object... objects) {
        if (logger.isDebugEnabled()) {
            logger.debug(getLogString(objects), t);
        }
    }

    private static String getLogString(Object... objects) {
        StringBuilder log = new StringBuilder();
        log.append(LEFT_TAG)
                .append(fetchTraceId()).append(SEP)
                .append(Thread.currentThread().getId()).append(SEP)
                .append(RIGHT_TAG);
        if (null != objects) {
            for (Object object : objects) {
                log.append(object);
            }
        }
        return log.toString();
    }

    /**
     * Fetch trova id string.
     *
     * @return the string
     */
    public static String fetchTraceId() {
        String traceId = ProcessContextHolder.get().getTraceId();
        return StringUtils.isEmpty(traceId) ? "-" : traceId;
    }
}
