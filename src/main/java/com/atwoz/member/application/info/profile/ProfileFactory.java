package com.atwoz.member.application.info.profile;

import com.atwoz.member.application.info.dto.profile.LocationUpdateRequest;
import com.atwoz.member.application.info.dto.profile.LocationWriteRequest;
import com.atwoz.member.application.info.dto.profile.PositionUpdateRequest;
import com.atwoz.member.application.info.dto.profile.PositionWriteRequest;
import com.atwoz.member.application.info.dto.profile.ProfileUpdateRequest;
import com.atwoz.member.application.info.dto.profile.ProfileWriteRequest;
import com.atwoz.member.domain.info.profile.Body;
import com.atwoz.member.domain.info.profile.Gender;
import com.atwoz.member.domain.info.profile.Job;
import com.atwoz.member.domain.info.profile.Location;
import com.atwoz.member.domain.info.profile.Position;
import com.atwoz.member.domain.info.profile.Profile;
import com.atwoz.member.domain.info.profile.YearManager;
import java.math.BigDecimal;

public class ProfileFactory {

    public static Profile createNewProfile(final Long memberId,
                                           final ProfileWriteRequest request,
                                           final YearManager yearManager) {
        int birthYear = request.birthYear();
        int height = request.height();
        Gender gender = Gender.findByName(request.gender());
        Body body = new Body(yearManager, birthYear, height, gender);

        LocationWriteRequest locationWriteRequest = request.location();
        String city = locationWriteRequest.city();
        String sector = locationWriteRequest.sector();
        Location location = new Location(city, sector);

        PositionWriteRequest positionWriteRequest = request.position();
        BigDecimal latitude = positionWriteRequest.latitude();
        BigDecimal longitude = positionWriteRequest.longitude();
        Position position = new Position(latitude, longitude);

        Job job = Job.findByCode(request.job());

        return new Profile(memberId, body, location, position, job);
    }

    public static Profile createUpdateProfile(final Long memberId,
                                              final ProfileUpdateRequest request,
                                              final YearManager yearManager) {
        int birthYear = request.birthYear();
        int height = request.height();
        Gender gender = Gender.findByName(request.gender());
        Body body = new Body(yearManager, birthYear, height, gender);

        LocationUpdateRequest locationUpdateRequest = request.location();
        String city = locationUpdateRequest.city();
        String sector = locationUpdateRequest.sector();
        Location location = new Location(city, sector);

        PositionUpdateRequest positionUpdateRequest = request.position();
        BigDecimal latitude = positionUpdateRequest.latitude();
        BigDecimal longitude = positionUpdateRequest.longitude();
        Position position = new Position(latitude, longitude);

        Job job = Job.findByCode(request.job());

        return new Profile(memberId, body, location, position, job);
    }
}
