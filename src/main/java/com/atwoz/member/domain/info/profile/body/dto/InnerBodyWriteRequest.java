package com.atwoz.member.domain.info.profile.body.dto;

import com.atwoz.member.application.info.profile.body.dto.BodyWriteRequest;
import com.atwoz.member.domain.info.profile.body.YearManager;

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
