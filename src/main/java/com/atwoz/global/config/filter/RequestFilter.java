package com.atwoz.global.config.filter;

import com.atwoz.global.config.CustomRequestWrapper;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import org.springframework.stereotype.Component;

@Component
public class RequestFilter implements Filter {
    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
            throws IOException, ServletException {
        CustomRequestWrapper customRequestWrapper = new CustomRequestWrapper((HttpServletRequest) request);
        chain.doFilter(customRequestWrapper, response);
    }
}
