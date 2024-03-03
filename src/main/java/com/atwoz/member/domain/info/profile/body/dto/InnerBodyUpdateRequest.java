package com.atwoz.member.domain.info.profile.body.dto;

import com.atwoz.member.application.info.profile.body.dto.BodyUpdateRequest;
import com.atwoz.member.domain.info.profile.body.YearManager;

public record InnerBodyUpdateRequest(
        YearManager yearManager,
        int birthYear,
        int height,
        String gender
) {

    public static InnerBodyUpdateRequest of(final YearManager yearManager, final BodyUpdateRequest request) {
        return new InnerBodyUpdateRequest(yearManager, request.birthYear(), request.height(), request.gender());
    }
}
