package com.atwoz.member.ui.auth.support.resolver;

import com.atwoz.member.ui.auth.support.oauth.OAuthAuthority;
import com.atwoz.member.ui.auth.support.oauth.OAuthProperties;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@RequiredArgsConstructor
@Component
public class OAuthArgumentResolver implements HandlerMethodArgumentResolver {

    private final OAuthProperties oAuthProperties;
    private final RequestProcessor requestProcessor;

    @Override
    public boolean supportsParameter(final MethodParameter parameter) {
        return parameter.hasParameterAnnotation(OAuthAuthority.class);
    }

    @Override
    public Object resolveArgument(final MethodParameter parameter,
                                  final ModelAndViewContainer mavContainer,
                                  final NativeWebRequest webRequest,
                                  final WebDataBinderFactory binderFactory) throws IOException {
        HttpServletRequest httpServletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        String providerName = requestProcessor.readJsonFromBody(httpServletRequest);

        return oAuthProperties.findByProviderName(providerName);
    }
}
