package com.atwoz.member.ui.info.dto;

import com.atwoz.member.domain.info.hobby.Hobby;
import com.atwoz.member.domain.info.hobby.MemberHobby;

public record HobbySearchResponse(

        String hobby
) {

    public static HobbySearchResponse from(final MemberHobby memberHobby) {
        Hobby hobby = memberHobby.getHobby();
        return new HobbySearchResponse(hobby.getCode());
    }
}
