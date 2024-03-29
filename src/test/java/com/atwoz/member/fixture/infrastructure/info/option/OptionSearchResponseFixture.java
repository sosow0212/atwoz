package com.atwoz.member.fixture.infrastructure.info.option;

import com.atwoz.member.domain.info.option.Drink;
import com.atwoz.member.domain.info.option.Graduate;
import com.atwoz.member.domain.info.option.Mbti;
import com.atwoz.member.domain.info.option.Religion;
import com.atwoz.member.domain.info.option.Smoke;
import com.atwoz.member.infrastructure.info.option.dto.OptionSearchResponse;

@SuppressWarnings("NonAsciiCharacters")
public class OptionSearchResponseFixture {

    public static OptionSearchResponse 회원_옵션_조회_응답() {
        String drink = Drink.NEVER.getName();
        String graduate = Graduate.SEOUL_FOURTH.getName();
        String religion = Religion.CHRIST.getName();
        String smoke = Smoke.NEVER.getName();
        String mbti = Mbti.INFJ.name();

        return new OptionSearchResponse(
                drink,
                graduate,
                religion,
                smoke,
                mbti
        );
    }
}
