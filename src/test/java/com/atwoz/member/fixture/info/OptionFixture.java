package com.atwoz.member.fixture.info;

import com.atwoz.member.domain.info.option.Drink;
import com.atwoz.member.domain.info.option.Graduate;
import com.atwoz.member.domain.info.option.Mbti;
import com.atwoz.member.domain.info.option.Option;
import com.atwoz.member.domain.info.option.Religion;
import com.atwoz.member.domain.info.option.Smoke;

@SuppressWarnings("NonAsciiCharacters")
public class OptionFixture {

    public static Option 회원_일반_옵션_생성() {
        Long memberId = 1L;
        Smoke smoke = Smoke.NEVER;
        Religion religion = Religion.CHRIST;
        Drink drink = Drink.NEVER;
        Mbti mbti = Mbti.INFJ;
        Graduate graduate = Graduate.SEOUL_FOURTH;

        return new Option(memberId, smoke, religion, drink, mbti, graduate);
    }

    public static Option 회원_수정_옵션_생성() {
        Long memberId = 1L;
        Smoke smoke = Smoke.ELECTRONIC;
        Religion religion = Religion.BUDDHA;
        Drink drink = Drink.ENJOY;
        Mbti mbti = Mbti.ISFJ;
        Graduate graduate = Graduate.DOCTOR;

        return new Option(memberId, smoke, religion, drink, mbti, graduate);
    }
}
