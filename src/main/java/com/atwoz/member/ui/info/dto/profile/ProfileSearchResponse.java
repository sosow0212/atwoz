package com.atwoz.member.ui.info.dto.profile;

import com.atwoz.member.domain.info.profile.Body;
import com.atwoz.member.domain.info.profile.Gender;
import com.atwoz.member.domain.info.profile.Job;
import com.atwoz.member.domain.info.profile.Location;
import com.atwoz.member.domain.info.profile.Position;
import com.atwoz.member.domain.info.profile.Profile;

public record ProfileSearchResponse(

        int age,

        int height,

        String gender,

        LocationSearchResponse location,

        PositionSearchResponse position,

        String job
) {
    public static ProfileSearchResponse from(final Profile profile) {
        Body body = profile.getBody();
        int year = body.getBirthYear();
        int height = body.getHeight();
        Gender gender = body.getGender();

        Location location = profile.getLocation();
        Position position = profile.getPosition();
        Job job = profile.getJob();

        return new ProfileSearchResponse(
                year,
                height,
                gender.getName(),
                LocationSearchResponse.from(location),
                PositionSearchResponse.from(position),
                job.getJob()
        );
    }
}
