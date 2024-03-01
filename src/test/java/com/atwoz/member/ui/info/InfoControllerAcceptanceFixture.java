package com.atwoz.member.ui.info;

import static com.atwoz.member.fixture.member.MemberFixture.일반_유저_생성;
import static org.assertj.core.api.Assertions.assertThat;

import com.atwoz.helper.IntegrationHelper;
import com.atwoz.member.application.info.dto.InfoWriteRequest;
import com.atwoz.member.domain.member.Member;
import com.atwoz.member.domain.member.MemberRepository;
import com.atwoz.member.infrastructure.auth.JwtTokenProvider;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class InfoControllerAcceptanceFixture extends IntegrationHelper {

    private static final String BASIC_URL = "http://localhost:";

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private MemberRepository memberRepository;

    protected Member 회원_생성() {
        return memberRepository.save(일반_유저_생성());
    }

    protected String 토큰_생성(final String email) {
        return jwtTokenProvider.createTokenWith(email);
    }

    protected ExtractableResponse 회원_정보를_생성한다(final String token, final InfoWriteRequest request, final String url) {
        return RestAssured.given().log().all()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(request)
                .post(url)
                .then().log().all()
                .extract();
    }

    protected void 회원_정보_생성_검증(final ExtractableResponse response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    protected ExtractableResponse 회원_정보를_조회한다(final String token, final String url) {
        return RestAssured.given().log().all()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .when()
                .get(url)
                .then().log().all()
                .extract();
    }

    protected void 회원_정보_조회_결과_검증(final ExtractableResponse response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }
}
