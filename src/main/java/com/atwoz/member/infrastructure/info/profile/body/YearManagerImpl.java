package com.atwoz.member.infrastructure.info.profile.body;

import com.atwoz.member.domain.info.profile.body.YearManager;
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
