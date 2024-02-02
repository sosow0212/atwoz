package com.atwoz.member.application.auth;

import com.atwoz.member.application.auth.dto.MemberInfo;
import com.atwoz.member.domain.auth.JsonMapper;
import com.atwoz.member.infrastructure.auth.OAuthFakeConnectionManager;
import com.atwoz.member.infrastructure.auth.dto.OAuthProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.atwoz.member.fixture.auth.OAuthProviderFixture.인증_기관_생성;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

@DisplayNameGeneration(ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
@ExtendWith(MockitoExtension.class)
class KakaoOAuthRequesterTest {

    @Mock
    private JsonMapper jsonMapper;
    private KakaoOAuthRequester kakaoOAuthRequester;

    @BeforeEach
    void init() {
        kakaoOAuthRequester = new KakaoOAuthRequester(new OAuthFakeConnectionManager(), jsonMapper);
    }

    @Test
    void 인증기관에_access_token을_요청한다() {
        // given
        String code = "testCode";
        OAuthProvider oAuthProvider = 인증_기관_생성();
        String expectedAccessToken = "accessToken";

        when(jsonMapper.extractAccessTokenFrom(anyString())).thenReturn(expectedAccessToken);

        // when
        String accessToken = kakaoOAuthRequester.getAccessToken(code, oAuthProvider);

        // then
        assertThat(accessToken).isEqualTo(expectedAccessToken);
    }

    @Test
    void 인증기관에_회원_정보를_요청한다() {
        // given
        String accessToken = "accessToken";
        OAuthProvider oAuthProvider = 인증_기관_생성();
        MemberInfo expectedMemberInfo = new MemberInfo("emial", "name");

        when(jsonMapper.extractMemberInfoFrom(anyString())).thenReturn(expectedMemberInfo);

        // when
        MemberInfo memberInfo = kakaoOAuthRequester.getMemberInfo(accessToken, oAuthProvider);

        // then
        assertThat(memberInfo).isEqualTo(expectedMemberInfo);
    }
}
