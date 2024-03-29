package com.atwoz.member.infrastructure.info;

import static com.atwoz.member.fixture.infrastructure.info.hobby.HobbiesSearchResponseFixture.회원_취미_조회_응답;
import static com.atwoz.member.fixture.infrastructure.info.option.OptionSearchResponseFixture.회원_옵션_조회_응답;
import static com.atwoz.member.fixture.infrastructure.info.profile.ProfileSearchResponseFixture.회원_프로필_조회_응답;
import static com.atwoz.member.fixture.infrastructure.info.style.StylesSearchResponseFixture.회원_스타일_조회_응답;

import com.atwoz.member.domain.info.InfoRepository;
import com.atwoz.member.infrastructure.info.dto.InfoSearchResponse;
import java.util.Optional;

@SuppressWarnings("NonAsciiCharacters")
public class InfoFakeRepository implements InfoRepository {

    @Override
    public Optional<InfoSearchResponse> findByMemberId(final Long memberId) {
        return Optional.of(new InfoSearchResponse(회원_프로필_조회_응답(), 회원_옵션_조회_응답(), 회원_취미_조회_응답(), 회원_스타일_조회_응답()));
    }
}
