package com.atwoz.member.ui.info;

import static com.atwoz.member.fixture.info.MemberHobbiesFixture.회원_일반_취미_생성;
import static com.atwoz.member.fixture.info.MemberStylesFixture.회원_일반_스타일_생성;
import static com.atwoz.member.fixture.info.OptionFixture.회원_일반_옵션_생성;
import static com.atwoz.member.fixture.info.ProfileFixture.회원_일반_프로필_생성;
import static com.atwoz.member.fixture.info.dto.response.HobbiesSearchResponseFixture.회원_취미_조회_응답;
import static com.atwoz.member.fixture.info.dto.response.OptionSearchResponseFixture.회원_옵션_조회_응답;
import static com.atwoz.member.fixture.info.dto.response.ProfileSearchResponseFixture.회원_프로필_조회_응답;
import static com.atwoz.member.fixture.info.dto.response.StylesSearchResponseFixture.회원_스타일_조회_응답;
import static com.atwoz.member.fixture.member.MemberFixture.일반_유저_생성;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import com.atwoz.helper.IntegrationHelper;
import com.atwoz.member.application.info.dto.InfoUpdateRequest;
import com.atwoz.member.application.info.dto.InfoWriteRequest;
import com.atwoz.member.domain.info.hobby.MemberHobbyRepository;
import com.atwoz.member.domain.info.option.OptionRepository;
import com.atwoz.member.domain.info.profile.ProfileRepository;
import com.atwoz.member.domain.info.style.MemberStyleRepository;
import com.atwoz.member.domain.member.Member;
import com.atwoz.member.domain.member.MemberRepository;
import com.atwoz.member.infrastructure.auth.JwtTokenProvider;
import com.atwoz.member.ui.info.dto.InfoSearchResponse;
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

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private OptionRepository optionRepository;

    @Autowired
    private MemberHobbyRepository memberHobbyRepository;

    @Autowired
    private MemberStyleRepository memberStyleRepository;

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

    protected void 회원_정보_생성() {
        profileRepository.save(회원_일반_프로필_생성());
        optionRepository.save(회원_일반_옵션_생성());
        memberHobbyRepository.saveAll(회원_일반_취미_생성());
        memberStyleRepository.saveAll(회원_일반_스타일_생성());
    }

    protected ExtractableResponse 회원_정보를_수정한다(final String token, final InfoUpdateRequest request, final String url) {
        return RestAssured.given().log().all()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .patch(url)
                .then().log().all()
                .extract();
    }

    protected void 회원_정보_수정_검증(final ExtractableResponse response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    protected void 회원_정보_수정_예외_검증(final ExtractableResponse response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.NOT_FOUND.value());
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
        InfoSearchResponse result = response.as(InfoSearchResponse.class);
        assertSoftly(softly -> {
            softly.assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
            softly.assertThat(result.profile()).isEqualTo(회원_프로필_조회_응답());
            softly.assertThat(result.option()).isEqualTo(회원_옵션_조회_응답());
            softly.assertThat(result.hobbies()).isEqualTo(회원_취미_조회_응답());
            softly.assertThat(result.styles()).isEqualTo(회원_스타일_조회_응답());
        });
    }

    protected void 회원_정보_조회_결과_예외_검증(final ExtractableResponse response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }
}
