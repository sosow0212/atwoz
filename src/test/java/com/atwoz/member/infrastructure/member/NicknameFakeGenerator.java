package com.atwoz.member.infrastructure.member;

import com.atwoz.member.domain.member.NicknameGenerator;

public class NicknameFakeGenerator implements NicknameGenerator {

    private static final String FAKE_NICKNAME = "nickname";

    @Override
    public String createRandomNickname() {
        return FAKE_NICKNAME;
    }
}
