package com.atwoz.member.domain.info.profile.year;

import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class YearManagerImpl implements YearManager {

    @Override
    public int getCurrentYear() {
        LocalDateTime now = LocalDateTime.now();
        return now.getYear();
    }
}
