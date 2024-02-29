package com.atwoz.member.application.info.option;

import com.atwoz.member.domain.info.option.Drink;
import com.atwoz.member.domain.info.option.Graduate;
import com.atwoz.member.domain.info.option.Mbti;
import com.atwoz.member.domain.info.option.Option;
import com.atwoz.member.domain.info.option.Religion;
import com.atwoz.member.domain.info.option.Smoke;

public class OptionFactory {

    public static Option createOption(final Long memberId,
                                      final String smokeName,
                                      final String religionName,
                                      final String drinkName,
                                      final String mbtiName,
                                      final String graduateName) {
        Smoke smoke = Smoke.findByName(smokeName);
        Religion religion = Religion.findByName(religionName);
        Drink drink = Drink.findByName(drinkName);
        Mbti mbti = Mbti.findByName(mbtiName);
        Graduate graduate = Graduate.findByName(graduateName);

        return new Option(memberId, smoke, religion, drink, mbti, graduate);
    }
}
