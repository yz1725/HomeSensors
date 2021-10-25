package com.letaotao.common.filter;

import com.letaotao.common.context.ProcessContextHolder;
import com.letaotao.common.utils.IdGenerator;
import org.springframework.core.annotation.Order;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/*", filterName = "traceIdRequestContextFilter")
@Order(-1)
public class TraceIdRequestContextFilter extends OncePerRequestFilter {
    private static final String TRACE_ID = "Trace-Id";
    private static final String REQUEST_ID = "Request-Id";

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException,
            IOException {
        final String traceId = IdGenerator.generate();
        ProcessContextHolder.get().setTraceId(traceId);
        httpServletResponse.setHeader(TRACE_ID, traceId);
        String requestId = httpServletRequest.getHeader(REQUEST_ID);
        if (!StringUtils.isEmpty(requestId)) {
            httpServletResponse.setHeader(REQUEST_ID, requestId);
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
        ProcessContextHolder.remove();
    }
}
