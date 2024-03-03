package com.atwoz.member.application.info.profile.dto;

import com.atwoz.member.domain.info.profile.body.dto.InnerBodyWriteRequest;
import com.atwoz.member.domain.info.profile.location.dto.InnerLocationWriteRequest;
import com.atwoz.member.domain.info.profile.position.dto.InnerPositionWriteRequest;
import com.atwoz.member.domain.info.profile.body.YearManager;

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
