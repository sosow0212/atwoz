package com.atwoz.member.domain.info.dto.profile;

import com.atwoz.member.application.info.dto.profile.ProfileUpdateRequest;
import com.atwoz.member.domain.info.dto.profile.body.InnerBodyUpdateRequest;
import com.atwoz.member.domain.info.dto.profile.location.InnerLocationUpdateRequest;
import com.atwoz.member.domain.info.dto.profile.position.InnerPositionUpdateRequest;
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
