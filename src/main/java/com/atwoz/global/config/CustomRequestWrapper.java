package com.atwoz.global.config;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class CustomRequestWrapper extends HttpServletRequestWrapper {

    private final Charset encoding;
    private final byte[] rawData;

    public CustomRequestWrapper(final HttpServletRequest request) throws IOException {
        super(request);

        String characterEncoding = request.getCharacterEncoding();
        if (StringUtils.isBlank(characterEncoding)) {
            characterEncoding = StandardCharsets.UTF_8.name();
        }
        this.encoding = Charset.forName(characterEncoding);

        try {
            InputStream inputStream = request.getInputStream();
            this.rawData = inputStream.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.rawData);
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            public int read() throws IOException {
                return byteArrayInputStream.read();
            }
        };
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(this.getInputStream(), this.encoding));
    }

    @Override
    public ServletRequest getRequest() {
        return super.getRequest();
    }
}
