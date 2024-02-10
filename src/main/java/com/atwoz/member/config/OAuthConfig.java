package com.atwoz.member.config;

import com.atwoz.member.ui.auth.support.resolver.OAuthArgumentResolver;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
@Configuration
public class OAuthConfig implements WebMvcConfigurer {

    private final OAuthArgumentResolver oAuthArgumentResolver;

    @Override
    public void addArgumentResolvers(final List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(oAuthArgumentResolver);
    }
}
