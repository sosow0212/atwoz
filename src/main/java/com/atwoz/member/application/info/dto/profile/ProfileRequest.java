package com.atwoz.member.application.info.dto.profile;

import com.atwoz.member.application.info.dto.profile.location.LocationRequest;
import com.atwoz.member.application.info.dto.profile.position.PositionRequest;

public interface ProfileRequest {

    Integer getBirthYear();
    Integer getHeight();
    String getGender();
    LocationRequest getLocation();
    PositionRequest getPosition();
    String getJob();
}
