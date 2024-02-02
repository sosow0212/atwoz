package com.atwoz.member.ui.auth.support.auth;

import com.atwoz.member.domain.auth.JsonMapper;
import jakarta.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HttpRequestProcessor implements RequestProcessor {

    private final JsonMapper jsonMapper;

    @Override
    public String readJsonFromBody(final HttpServletRequest httpServletRequest) throws IOException {
        BufferedReader bufferedReader = httpServletRequest.getReader();
        String requestBody = bufferedReader.lines()
                .filter(Objects::nonNull)
                .collect(Collectors.joining());

        return jsonMapper.extractValueForKey(requestBody, "provider");
    }
}
