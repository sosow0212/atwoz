package com.atwoz.member.domain.info.option.dto;

import com.atwoz.member.application.info.dto.option.OptionWriteRequest;

public record InnerOptionWriteRequest(
        Long memberId,
        String smokeName,
        String religionName,
        String drinkName,
        String mbtiName,
        String graduateName
) {

    public static InnerOptionWriteRequest of(final Long memberId, final OptionWriteRequest request) {
        return new InnerOptionWriteRequest(
                memberId,
                request.smoke(),
                request.religion(),
                request.drink(),
                request.mbti(),
                request.graduate()
        );
    }
}
