package com.atwoz.member.application.info.profile;

import com.atwoz.member.application.info.dto.profile.LocationWriteRequest;
import com.atwoz.member.application.info.dto.profile.ProfileWriteRequest;
import com.atwoz.member.domain.info.profile.Body;
import com.atwoz.member.domain.info.profile.Gender;
import com.atwoz.member.domain.info.profile.Job;
import com.atwoz.member.domain.info.profile.Location;
import com.atwoz.member.domain.info.profile.Profile;
import org.springframework.stereotype.Component;

@Component
public class ProfileFactory {

    public Profile fromRequest(final Long memberId, final ProfileWriteRequest request) {
        Body body = createMemberBody(request);
        Location location = createLocation(request.location());
        Job job = createJob(request.job());
        return new Profile(memberId, body, location, job);
    }

    private Body createMemberBody(final ProfileWriteRequest request) {
        Integer age = request.age();
        Integer height = request.height();
        Gender gender = Gender.findByName(request.gender());

        return new Body(age, height, gender);
    }

    private Location createLocation(final LocationWriteRequest request) {
        return new Location(request.city(), request.sector());
    }

    private Job createJob(final String job) {
        return new Job(job);
    }
}
