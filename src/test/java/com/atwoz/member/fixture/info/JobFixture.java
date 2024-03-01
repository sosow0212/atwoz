package com.atwoz.member.fixture.info;

import com.atwoz.member.domain.info.profile.Job;

@SuppressWarnings("NonAsciiCharacters")
public class JobFixture {

    public static Job 회원_일반_직업_생성() {
        return Job.RESEARCH_AND_DEVELOP;
    }

    public static Job 회원_수정_직업_생성() {
        return Job.LAW;
    }
}
