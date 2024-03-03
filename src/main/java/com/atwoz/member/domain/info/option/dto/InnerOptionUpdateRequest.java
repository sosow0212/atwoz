package com.atwoz.member.domain.info.option.dto;

import com.atwoz.member.application.info.option.dto.OptionUpdateRequest;

public record InnerOptionUpdateRequest(
        String smokeName,
        String religionName,
        String drinkName,
        String mbtiName,
        String graduateName
) {

    public static InnerOptionUpdateRequest from(final OptionUpdateRequest request) {
        return new InnerOptionUpdateRequest(
          request.smoke(),
          request.religion(),
          request.drink(),
          request.mbti(),
          request.graduate()
        );
    }
}
