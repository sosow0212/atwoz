package com.atwoz.member.domain.info.dto.profile.body;

import com.atwoz.member.application.info.dto.profile.body.BodyUpdateRequest;
import com.atwoz.member.domain.info.profile.YearManager;

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
