package com.atwoz.member.ui.auth.support.oauth;

import com.atwoz.helper.IntegrationHelper;
import com.atwoz.member.infrastructure.auth.dto.OAuthProvider;
import com.atwoz.member.ui.auth.support.auth.OAuthPlatform;
import com.atwoz.member.ui.auth.support.auth.OAuthProperties;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class OAuthPropertiesTest extends IntegrationHelper {

    @Autowired
    private OAuthProperties oAuthProperties;

    @Test
    void 인증기관_이름으로_OAuthProvider를_찾아_반환한다() {
        // given
        OAuthPlatform oAuthPlatform = OAuthPlatform.KAKAO;

        // when
        OAuthProvider oAuthProvider = oAuthProperties.findByProviderName(oAuthPlatform.name());

        // then
        assertThat(oAuthProvider).usingRecursiveComparison()
                .isEqualTo(oAuthProperties.getProperties().get(oAuthPlatform));
    }
}
