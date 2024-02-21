package com.atwoz.member.domain.info.hobby;

import com.atwoz.member.exception.exceptions.info.hobby.HobbyNotFoundException;
import com.atwoz.member.exception.exceptions.info.hobby.HobbySizeException;
import lombok.Getter;
import java.util.Arrays;
import java.util.List;

@Getter
public enum HobbyName {

    TRIP("국내여행 · 해외여행"),
    GOLF("골프"),
    THEATER("공연 · 전시회관람"),
    WRITE("글쓰기"),
    GAME("PC · 모바일 게임"),
    SING("노래"),
    DANCE("댄스"),
    DRAMA("드라마 · 예능보기"),
    DRIVE("드라이브"),
    CLIMB("등산 · 클라이밍"),
    RUNNING("러닝"),
    EAT("맛집탐방"),
    TENNIS("배드민턴 · 테니스"),
    BOARD_GAME("보드게임"),
    PHOTO("사진촬영"),
    WALK("산책"),
    SHOPPING("쇼핑"),
    SKI("스키 · 스노우보드"),
    INSTRUMENT("악기연주"),
    ANIMATION("애니메이션"),
    MOVIE("연극 · 영화"),
    WINE("와인"),
    HEALTH("운동 · 헬스"),
    YOGA("요가 · 필라테스"),
    COOK("요리"),
    CARTOON("웹툰 · 만화"),
    INTERIOR("인테리어"),
    BICYCLE("자전거"),
    CAMP("캠핑");

    private static final int MAX_SIZE = 3;

    private final String name;

    HobbyName(final String name) {
        this.name = name;
    }

    public static List<HobbyName> findAllByNames(final List<String> names) {
        validateSize(names);
        return names.stream()
                .map(HobbyName::from)
                .toList();
    }

    public static void validateSize(final List<String> names) {
        if (names.isEmpty()) {
            throw new HobbySizeException();
        }

        int validSize = Arrays.stream(values())
                .filter(hobbyName -> names.contains(hobbyName.getName()))
                .toList()
                .size();
        if (validSize > MAX_SIZE) {
            throw new HobbySizeException();
        }
    }

    private static HobbyName from(final String name) {
        return Arrays.stream(values())
                .filter(hobbyName -> hobbyName.isSame(name))
                .findFirst()
                .orElseThrow(HobbyNotFoundException::new);
    }

    private boolean isSame(final String name) {
        return this.name.equals(name);
    }
}
