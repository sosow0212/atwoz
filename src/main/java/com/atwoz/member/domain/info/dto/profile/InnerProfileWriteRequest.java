package com.atwoz.member.domain.info.dto.profile;

import com.atwoz.member.application.info.dto.profile.ProfileWriteRequest;
import com.atwoz.member.domain.info.dto.profile.body.InnerBodyWriteRequest;
import com.atwoz.member.domain.info.dto.profile.location.InnerLocationWriteRequest;
import com.atwoz.member.domain.info.dto.profile.position.InnerPositionWriteRequest;
import com.atwoz.member.domain.info.profile.YearManager;

public record InnerProfileWriteRequest(
        Long memberId,
        InnerBodyWriteRequest body,
        InnerLocationWriteRequest location,
        InnerPositionWriteRequest position,
        String jobCode
) {

    public static InnerProfileWriteRequest of(
            final Long memberId,
            final YearManager yearManager,
            final ProfileWriteRequest request) {
        return new InnerProfileWriteRequest(
                memberId,
                InnerBodyWriteRequest.of(yearManager, request.body()),
                InnerLocationWriteRequest.from(request.location()),
                InnerPositionWriteRequest.from(request.position()),
                request.job()
        );
    }
}
