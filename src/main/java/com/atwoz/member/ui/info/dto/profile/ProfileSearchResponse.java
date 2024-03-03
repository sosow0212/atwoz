package com.atwoz.member.ui.info.dto.profile;

import com.atwoz.member.domain.info.profile.body.Gender;
import com.atwoz.member.ui.info.dto.ProfileAndOptionSearchResponse;

public record ProfileSearchResponse(
        BodySearchResponse body,
        LocationSearchResponse location,
        PositionSearchResponse position,
        String job
) {
    public static ProfileSearchResponse from(final ProfileAndOptionSearchResponse response) {
        int age = response.getAge();
        int height = response.getHeight();
        Gender gender = response.getGender();
        BodySearchResponse body = new BodySearchResponse(age, height, gender.getName());

        LocationSearchResponse location = new LocationSearchResponse(response.getCity(), response.getSector());
        PositionSearchResponse position = new PositionSearchResponse(response.getLatitude(), response.getLongitude());
        String job = response.getJob().getCode();

        return new ProfileSearchResponse(body, location, position, job);
    }
}
