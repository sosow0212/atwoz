package com.atwoz.member.application.info.option;

import com.atwoz.member.application.info.dto.option.OptionWriteRequest;
import com.atwoz.member.domain.info.option.Option;
import org.springframework.stereotype.Component;

@Component
public class OptionFactory {

    public Option fromRequest(final Long memberId, final OptionWriteRequest request) {

        return new Option(memberId, request.smoke(), request.religion(), request.drink(), request.mbti(), request.graduate());
    }
}
