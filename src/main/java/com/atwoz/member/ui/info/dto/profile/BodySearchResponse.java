package com.atwoz.member.ui.info.dto.profile;

import com.atwoz.member.domain.info.profile.body.Body;

public record BodySearchResponse(
        int age,
        int height,
        String gender
) {

    public static BodySearchResponse from(final Body body) {
        return new BodySearchResponse(body.getAge(), body.getHeight(), body.getGender().getName());
    }
}
