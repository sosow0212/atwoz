package com.atwoz.member.infrastructure.info;

import static com.atwoz.member.fixture.domain.info.hobby.MemberHobbiesFixture.회원_일반_취미_생성;
import static com.atwoz.member.fixture.domain.info.option.OptionFixture.회원_일반_옵션_생성;
import static com.atwoz.member.fixture.domain.info.profile.ProfileFixture.회원_일반_프로필_생성;
import static com.atwoz.member.fixture.domain.info.style.MemberStylesFixture.회원_일반_스타일_생성;
import static com.atwoz.member.fixture.domain.member.MemberFixture.일반_유저_생성;
import static com.atwoz.member.fixture.infrastructure.info.InfoSearchResponseFixture.회원_정보_조회_응답;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import com.atwoz.config.TestConfig;
import com.atwoz.member.domain.member.Member;
import com.atwoz.member.infrastructure.info.dto.InfoSearchResponse;
import com.atwoz.member.infrastructure.info.hobby.MemberHobbyJpaRepository;
import com.atwoz.member.infrastructure.info.option.OptionJpaRepository;
import com.atwoz.member.infrastructure.info.profile.ProfileJpaRepository;
import com.atwoz.member.infrastructure.info.style.MemberStyleJpaRepository;
import com.atwoz.member.infrastructure.member.MemberJpaRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import java.util.Optional;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
@Import(TestConfig.class)
@DataJpaTest
public class InfoQueryRepositoryTest {

    @Autowired
    private MemberJpaRepository memberJpaRepository;

    @Autowired
    private ProfileJpaRepository profileJpaRepository;

    @Autowired
    private OptionJpaRepository optionJpaRepository;

    @Autowired
    private MemberHobbyJpaRepository memberHobbyJpaRepository;

    @Autowired
    private MemberStyleJpaRepository memberStyleJpaRepository;

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    private InfoQueryRepository infoQueryRepository;

    private Member member;

    @BeforeEach
    void init() {
        member = memberJpaRepository.save(일반_유저_생성());
        profileJpaRepository.save(회원_일반_프로필_생성());
        optionJpaRepository.save(회원_일반_옵션_생성());
        memberHobbyJpaRepository.saveAll(회원_일반_취미_생성());
        memberStyleJpaRepository.saveAll(회원_일반_스타일_생성());
        infoQueryRepository = new InfoQueryRepository(jpaQueryFactory);
    }

    @Test
    void 회원_정보를_조회한다() {
        // given
        InfoSearchResponse expectedResponse = 회원_정보_조회_응답();

        // when
        Optional<InfoSearchResponse> response = infoQueryRepository.findByMemberId(member.getId());

        // then
        assertSoftly(softly -> {
            softly.assertThat(response).isPresent();
            softly.assertThat(response.get()).usingRecursiveComparison()
                    .isEqualTo(expectedResponse);
        });
    }
}
