package com.atwoz.member.domain.info.dto.option;

import com.atwoz.member.application.info.dto.option.OptionUpdateRequest;

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
