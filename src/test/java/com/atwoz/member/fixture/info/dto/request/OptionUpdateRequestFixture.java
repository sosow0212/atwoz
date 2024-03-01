package com.atwoz.member.fixture.info.dto.request;

import com.atwoz.member.application.info.dto.option.OptionUpdateRequest;
import com.atwoz.member.domain.info.option.Drink;
import com.atwoz.member.domain.info.option.Graduate;
import com.atwoz.member.domain.info.option.Mbti;
import com.atwoz.member.domain.info.option.Religion;
import com.atwoz.member.domain.info.option.Smoke;

@SuppressWarnings("NonAsciiCharacters")
public class OptionUpdateRequestFixture {

    public static OptionUpdateRequest 회원_옵션_수정_요청() {
        String drink = Drink.ENJOY.getName();
        String graduate = Graduate.DOCTOR.getName();
        String religion = Religion.BUDDHA.getName();
        String smoke = Smoke.ELECTRONIC.getName();
        String mbti = Mbti.ISFJ.name();

        return new OptionUpdateRequest(
                drink,
                graduate,
                religion,
                smoke,
                mbti
        );
    }
}
