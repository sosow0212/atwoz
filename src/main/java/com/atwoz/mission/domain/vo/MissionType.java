package com.atwoz.mission.domain.vo;

import com.atwoz.mission.exception.MissionTypeInvalidException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
public enum MissionType {

    DAILY("daily", "일일"),
    CHALLENGE("challenge", "도전");

    private final String name;
    private final String value;

    public static MissionType from(final String missionType) {
        String missionName = missionType.toLowerCase();

        return Arrays.stream(values())
                .filter(it -> it.isSame(missionName))
                .findAny()
                .orElseThrow(MissionTypeInvalidException::new);
    }

    public boolean isSame(final String mission) {
        return this.name.equals(mission);
    }
}
