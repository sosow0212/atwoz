package com.atwoz.member.ui.info.dto;

import com.atwoz.member.domain.info.hobby.Hobby;
import com.atwoz.member.domain.info.hobby.HobbyName;

public record HobbySearchResponse(

        String hobby
) {

    public static HobbySearchResponse from(final Hobby hobby) {
        HobbyName hobbyName = hobby.getHobbyName();
        return new HobbySearchResponse(hobbyName.getName());
    }
}
