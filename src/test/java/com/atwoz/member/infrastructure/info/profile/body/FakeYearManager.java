package com.atwoz.member.infrastructure.info.profile.body;

import com.atwoz.member.domain.info.profile.body.YearManager;

public class FakeYearManager implements YearManager {

    private static final int YEAR = 2024;

    @Override
    public int getCurrentYear() {
        return YEAR;
    }
}
