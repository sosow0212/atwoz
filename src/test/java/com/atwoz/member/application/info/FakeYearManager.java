package com.atwoz.member.application.info;

import com.atwoz.member.domain.info.profile.year.YearManager;

public class FakeYearManager implements YearManager {

    private static final int YEAR = 2024;

    @Override
    public int getCurrentYear() {
        return YEAR;
    }
}
