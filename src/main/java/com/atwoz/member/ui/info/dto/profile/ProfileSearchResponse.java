package com.atwoz.member.ui.info.dto.profile;

import com.atwoz.member.domain.info.profile.Body;
import com.atwoz.member.domain.info.profile.Job;
import com.atwoz.member.domain.info.profile.Location;
import com.atwoz.member.domain.info.profile.Position;
import com.atwoz.member.domain.info.profile.Profile;

public record ProfileSearchResponse(
        BodySearchResponse body,
        LocationSearchResponse location,
        PositionSearchResponse position,
        String job
) {

    public static ProfileSearchResponse from(final Profile profile) {
        Body body = profile.getBody();
        Location location = profile.getLocation();
        Position position = profile.getPosition();
        Job job = profile.getJob();

        return new ProfileSearchResponse(
                BodySearchResponse.from(body),
                LocationSearchResponse.from(location),
                PositionSearchResponse.from(position),
                job.getCode()
        );
    }
}
