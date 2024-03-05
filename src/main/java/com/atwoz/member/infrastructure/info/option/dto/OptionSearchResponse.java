package com.atwoz.member.infrastructure.info.option.dto;

import com.atwoz.member.domain.info.option.Drink;
import com.atwoz.member.domain.info.option.Graduate;
import com.atwoz.member.domain.info.option.Mbti;
import com.atwoz.member.domain.info.option.Religion;
import com.atwoz.member.domain.info.option.Smoke;
import com.atwoz.member.infrastructure.info.dto.ProfileAndOptionSearchResponse;

public record OptionSearchResponse(
        String drink,
        String graduate,
        String religion,
        String smoke,
        String mbti
) {

    public static OptionSearchResponse from(final ProfileAndOptionSearchResponse response) {
        Drink drink = response.getDrink();
        Graduate graduate = response.getGraduate();
        Religion religion = response.getReligion();
        Smoke smoke = response.getSmoke();
        Mbti mbti = response.getMbti();

        return new OptionSearchResponse(
                drink.getName(),
                graduate.getName(),
                religion.getName(),
                smoke.getName(),
                mbti.name()
        );
    }
}
