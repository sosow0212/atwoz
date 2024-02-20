package com.atwoz.member.application.info.option;

import com.atwoz.member.application.info.dto.option.OptionWriteRequest;
import com.atwoz.member.domain.info.option.Drink;
import com.atwoz.member.domain.info.option.Graduate;
import com.atwoz.member.domain.info.option.Mbti;
import com.atwoz.member.domain.info.option.Option;
import com.atwoz.member.domain.info.option.Religion;
import com.atwoz.member.domain.info.option.Smoke;
import org.springframework.stereotype.Component;

@Component
public class OptionFactory {

    public Option fromRequest(final Long memberId, final OptionWriteRequest request) {

        Smoke smoke = Smoke.findByName(request.smoke());
        Religion religion = Religion.findByName(request.religion());
        Drink drink = Drink.findByName(request.drink());
        Mbti mbti = Mbti.findByName(request.mbti());
        Graduate graduate = Graduate.findByName(request.graduate());

        return new Option(memberId, smoke, religion, drink, mbti, graduate);
    }
}
