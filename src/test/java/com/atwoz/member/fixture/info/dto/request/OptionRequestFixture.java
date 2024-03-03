package com.atwoz.member.fixture.info.dto.request;

import com.atwoz.member.application.info.option.dto.OptionUpdateRequest;
import com.atwoz.member.application.info.option.dto.OptionWriteRequest;
import com.atwoz.member.domain.info.option.Drink;
import com.atwoz.member.domain.info.option.Graduate;
import com.atwoz.member.domain.info.option.Mbti;
import com.atwoz.member.domain.info.option.Religion;
import com.atwoz.member.domain.info.option.Smoke;
import com.atwoz.member.domain.info.option.dto.InnerOptionUpdateRequest;

@SuppressWarnings("NonAsciiCharacters")
public class OptionRequestFixture {

    public static OptionWriteRequest 회원_옵션_생성_요청() {
        String drink = Drink.NEVER.getName();
        String graduate = Graduate.SEOUL_FOURTH.getName();
        String religion = Religion.CHRIST.getName();
        String smoke = Smoke.NEVER.getName();
        String mbti = Mbti.INFJ.name();

        return new OptionWriteRequest(
                drink,
                graduate,
                religion,
                smoke,
                mbti
        );
    }

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

    public static InnerOptionUpdateRequest 회원_옵션_수정_요청_내부() {
        String smoke = Smoke.ELECTRONIC.getName();
        String religion = Religion.BUDDHA.getName();
        String drink = Drink.ENJOY.getName();
        String mbti = Mbti.ISFJ.name();
        String graduate = Graduate.DOCTOR.getName();

        return new InnerOptionUpdateRequest(
                smoke,
                religion,
                drink,
                mbti,
                graduate
        );
    }
}
