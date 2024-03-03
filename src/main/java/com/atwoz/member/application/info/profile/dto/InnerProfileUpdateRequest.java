package com.atwoz.member.application.info.profile.dto;

import com.atwoz.member.domain.info.profile.body.dto.InnerBodyUpdateRequest;
import com.atwoz.member.domain.info.profile.location.dto.InnerLocationUpdateRequest;
import com.atwoz.member.domain.info.profile.position.dto.InnerPositionUpdateRequest;
import com.atwoz.member.domain.info.profile.YearManager;

public record InnerProfileUpdateRequest(
        Long memberId,
        InnerBodyUpdateRequest body,
        InnerLocationUpdateRequest location,
        InnerPositionUpdateRequest position,
        String jobCode
) {

    public static InnerProfileUpdateRequest of(
            final Long memberId,
            final YearManager yearManager,
            final ProfileUpdateRequest request) {
        return new InnerProfileUpdateRequest(
                memberId,
                InnerBodyUpdateRequest.of(yearManager, request.body()),
                InnerLocationUpdateRequest.from(request.location()),
                InnerPositionUpdateRequest.from(request.position()),
                request.job()
        );
    }
}
