package com.atwoz.member.domain.info.dto.profile.body;

import com.atwoz.member.application.info.dto.profile.body.BodyWriteRequest;
import com.atwoz.member.domain.info.profile.YearManager;

public record InnerBodyWriteRequest(
        YearManager yearManager,
        int birthYear,
        int height,
        String gender
) {

    public static InnerBodyWriteRequest of(final YearManager yearManager, final BodyWriteRequest request) {
        return new InnerBodyWriteRequest(yearManager, request.birthYear(), request.height(), request.gender());
    }
}
