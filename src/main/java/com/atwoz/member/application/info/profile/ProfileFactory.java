package com.atwoz.member.application.info.profile;

import com.atwoz.member.application.info.dto.profile.LocationWriteRequest;
import com.atwoz.member.application.info.dto.profile.PositionWriteRequest;
import com.atwoz.member.application.info.dto.profile.ProfileWriteRequest;
import com.atwoz.member.domain.info.profile.Body;
import com.atwoz.member.domain.info.profile.Gender;
import com.atwoz.member.domain.info.profile.Job;
import com.atwoz.member.domain.info.profile.Location;
import com.atwoz.member.domain.info.profile.Position;
import com.atwoz.member.domain.info.profile.Profile;
import com.atwoz.member.domain.info.profile.year.YearManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProfileFactory {

    private final YearManager yearManager;

    public Profile fromRequest(final Long memberId, final ProfileWriteRequest request) {
        Body body = createMemberBody(request);
        Location location = createLocation(request.location());
        Position position = createPosition(request.position());
        Job job = createJob(request.job());
        return new Profile(memberId, body, location, position, job);
    }

    private Body createMemberBody(final ProfileWriteRequest request) {
        int currentYear = yearManager.getCurrentYear();
        int birthYear = request.birthYear();
        int height = request.height();
        Gender gender = Gender.findByName(request.gender());

        return new Body(currentYear, birthYear, height, gender);
    }

    private Location createLocation(final LocationWriteRequest request) {
        return new Location(request.city(), request.sector());
    }

    private Position createPosition(final PositionWriteRequest request) {
        return new Position(request.latitude(), request.longitude());
    }

    private Job createJob(final String job) {
        return new Job(job);
    }
}
