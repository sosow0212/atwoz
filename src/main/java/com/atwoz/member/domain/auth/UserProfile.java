package com.atwoz.member.domain.auth;

import com.atwoz.member.domain.member.Member;
import lombok.Builder;
import lombok.Getter;

import static com.atwoz.member.domain.member.MemberRole.MEMBER;

@Getter
public class UserProfile {

    private final String oauthId;
    private final String email;
    private final String name;

    @Builder
    public UserProfile(final String oauthId, final String email, final String name) {
        this.oauthId = oauthId;
        this.email = email;
        this.name = name;
    }

    public Member toMember() {
        return Member.builder()
                .password(oauthId)
                .email(email)
                .nickname(name)
                .memberRole(MEMBER)
                .build();
    }
}
