package com.atwoz.member.ui.info;

import static com.atwoz.member.fixture.application.info.InfoRequestFixture.회원_정보_생성_요청;
import static com.atwoz.member.fixture.application.info.InfoRequestFixture.회원_정보_수정_요청;

import com.atwoz.member.domain.member.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class InfoControllerAcceptanceTest extends InfoControllerAcceptanceFixture {

    private static final String 회원_정보_생성_요청_url = "/api/info";
    private static final String 회원_정보_수정_요청_url = "/api/info";
    private static final String 회원_정보_조회_요청_url = "/api/info";

    private Member 회원;
    private String 토큰;

    @BeforeEach
    void setup() {
        회원 = 회원_생성();
        토큰 = 토큰_생성(회원.getEmail());
    }

    @Test
    void 회원의_정보를_생성한다() {
        // given
        var 회원_정보_생성_요청서 = 회원_정보_생성_요청();

        // when
        var 회원_정보_생성_요청_결과 = 회원_정보를_생성한다(토큰, 회원_정보_생성_요청서, 회원_정보_생성_요청_url);

        // then
        회원_정보_생성_검증(회원_정보_생성_요청_결과);
    }

    @Test
    void 회원의_정보를_수정한다() {
        // given
        회원_정보_생성();
        var 회원_정보_수정_요청서 = 회원_정보_수정_요청();

        // when
        var 회원_정보_수정_요청_결과 = 회원_정보를_수정한다(토큰, 회원_정보_수정_요청서, 회원_정보_수정_요청_url);

        // then
        회원_정보_수정_검증(회원_정보_수정_요청_결과);
    }

    @Test
    void 회원_정보가_없으면_수정할_수_없다() {
        // given
        var 회원_정보_수정_요청서 = 회원_정보_수정_요청();

        // when
        var 회원_정보_수정_요청_결과 = 회원_정보를_수정한다(토큰, 회원_정보_수정_요청서, 회원_정보_수정_요청_url);

        // then
        회원_정보_수정_예외_검증(회원_정보_수정_요청_결과);
    }

    @Test
    void 회원의_정보를_조회한다() {
        // given
        회원_정보_생성();

        // when
        var 회원_정보_조회_결과 = 회원_정보를_조회한다(토큰, 회원_정보_조회_요청_url);

        // then
        회원_정보_조회_결과_검증(회원_정보_조회_결과);
    }

    @Test
    void 회원_정보가_없으면_조회할_수_없다() {
        // when
        var 회원_정보_조회_결과 = 회원_정보를_조회한다(토큰, 회원_정보_조회_요청_url);

        // then
        회원_정보_조회_결과_예외_검증(회원_정보_조회_결과);
    }
}
