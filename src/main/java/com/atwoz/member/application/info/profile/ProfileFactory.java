package com.atwoz.member.application.info.profile;

import com.atwoz.member.application.info.dto.profile.ProfileRequest;
import com.atwoz.member.application.info.dto.profile.location.LocationRequest;
import com.atwoz.member.application.info.dto.profile.position.PositionRequest;
import com.atwoz.member.domain.info.profile.Body;
import com.atwoz.member.domain.info.profile.Gender;
import com.atwoz.member.domain.info.profile.Job;
import com.atwoz.member.domain.info.profile.Location;
import com.atwoz.member.domain.info.profile.Position;
import com.atwoz.member.domain.info.profile.Profile;
import com.atwoz.member.domain.info.profile.YearManager;
import java.math.BigDecimal;

public class ProfileFactory {

    public static Profile createProfile(final Long memberId, final ProfileRequest request, final YearManager yearManager) {
        int birthYear = request.getBirthYear();
        int height = request.getHeight();
        Gender gender = Gender.findByName(request.getGender());
        Body body = new Body(yearManager, birthYear, height, gender);

        LocationRequest locationWriteRequest = request.getLocation();
        String city = locationWriteRequest.getCity();
        String sector = locationWriteRequest.getSector();
        Location location = new Location(city, sector);

        PositionRequest positionWriteRequest = request.getPosition();
        BigDecimal latitude = positionWriteRequest.getLatitude();
        BigDecimal longitude = positionWriteRequest.getLongitude();
        Position position = new Position(latitude, longitude);

        Job job = Job.findByCode(request.getJob());

        return new Profile(memberId, body, location, position, job);
    }
}
