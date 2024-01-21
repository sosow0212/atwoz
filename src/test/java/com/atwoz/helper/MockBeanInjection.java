package com.atwoz.helper;

import com.atwoz.member.application.auth.AuthService;
import com.atwoz.member.domain.auth.TokenProvider;
import com.atwoz.member.ui.auth.support.AuthenticationContext;
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
}
