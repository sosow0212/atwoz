package com.atwoz.helper;

import com.atwoz.member.application.auth.AuthService;
import com.atwoz.member.application.info.hobby.MemberHobbyService;
import com.atwoz.member.application.info.option.OptionService;
import com.atwoz.member.application.info.profile.ProfileService;
import com.atwoz.member.application.info.style.MemberStyleService;
import com.atwoz.member.domain.auth.TokenProvider;
import com.atwoz.member.ui.auth.interceptor.LoginValidCheckerInterceptor;
import com.atwoz.member.ui.auth.interceptor.ParseMemberIdFromTokenInterceptor;
import com.atwoz.member.ui.auth.support.auth.AuthenticationContext;
import com.atwoz.member.ui.auth.support.auth.OAuthProperties;
import com.atwoz.member.ui.auth.support.resolver.AuthArgumentResolver;
import com.atwoz.member.ui.auth.support.resolver.OAuthArgumentResolver;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;

@MockBean(JpaMetamodelMappingContext.class)
public class MockBeanInjection {

    @MockBean
    protected TokenProvider tokenProvider;

    @MockBean
    protected AuthenticationContext authenticationContext;

    @MockBean
    protected AuthService authService;

    @MockBean
    protected MemberHobbyService memberHobbyService;

    @MockBean
    protected OptionService optionService;

    @MockBean
    protected ProfileService profileService;

    @MockBean
    protected MemberStyleService memberStyleService;

    @MockBean
    protected OAuthProperties oAuthProperties;

    @MockBean
    protected OAuthArgumentResolver oAuthArgumentResolver;

    @MockBean
    protected AuthArgumentResolver authArgumentResolver;

    @MockBean
    protected ParseMemberIdFromTokenInterceptor parseMemberIdFromTokenInterceptor;

    @MockBean
    protected LoginValidCheckerInterceptor loginValidCheckerInterceptor;
}
