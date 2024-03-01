package com.atwoz.member.ui.info.dto.option;

import com.atwoz.member.domain.info.option.Drink;
import com.atwoz.member.domain.info.option.Graduate;
import com.atwoz.member.domain.info.option.Mbti;
import com.atwoz.member.domain.info.option.Option;
import com.atwoz.member.domain.info.option.Religion;
import com.atwoz.member.domain.info.option.Smoke;

public record OptionSearchResponse(
        String drink,
        String graduate,
        String religion,
        String smoke,
        String mbti
) {

    public static OptionSearchResponse from(final Option option) {
        Drink drink = option.getDrink();
        Graduate graduate = option.getGraduate();
        Religion religion = option.getReligion();
        Smoke smoke = option.getSmoke();
        Mbti mbti = option.getMbti();

        return new OptionSearchResponse(
                drink.getName(),
                graduate.getName(),
                religion.getName(),
                smoke.getName(),
                mbti.name()
        );
    }
}
