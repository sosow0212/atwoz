package com.atwoz.member.application.info.option;

import com.atwoz.member.application.info.dto.option.OptionRequest;
import com.atwoz.member.domain.info.option.Drink;
import com.atwoz.member.domain.info.option.Graduate;
import com.atwoz.member.domain.info.option.Mbti;
import com.atwoz.member.domain.info.option.Option;
import com.atwoz.member.domain.info.option.Religion;
import com.atwoz.member.domain.info.option.Smoke;

public class OptionFactory {

    public static Option createOption(final Long memberId, final OptionRequest optionRequest) {
        Smoke smoke = Smoke.findByName(optionRequest.getSmoke());
        Religion religion = Religion.findByName(optionRequest.getReligion());
        Drink drink = Drink.findByName(optionRequest.getDrink());
        Mbti mbti = Mbti.findByName(optionRequest.getMbti());
        Graduate graduate = Graduate.findByName(optionRequest.getGraduate());

        return new Option(memberId, smoke, religion, drink, mbti, graduate);
    }
}
