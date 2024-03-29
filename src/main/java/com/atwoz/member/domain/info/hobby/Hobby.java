package com.atwoz.member.domain.info.hobby;

import com.atwoz.member.exception.exceptions.info.hobby.HobbyDuplicateException;
import com.atwoz.member.exception.exceptions.info.hobby.InvalidHobbyException;
import com.atwoz.member.exception.exceptions.info.hobby.HobbySizeException;
import lombok.Getter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
public enum Hobby {

    TRIP("국내여행/해외여행", "B001"),
    GOLF("골프", "B002"),
    THEATER("공연/전시회관람", "B003"),
    WRITE("글쓰기", "B004"),
    GAME("PC/모바일 게임", "B005"),
    SING("노래", "B006"),
    DANCE("댄스", "B007"),
    DRAMA("드라마/예능보기", "B008"),
    DRIVE("드라이브", "B009"),
    CLIMB("등산/클라이밍", "B010"),
    RUNNING("러닝", "B011"),
    EAT("맛집탐방", "B012"),
    TENNIS("배드민턴/테니스", "B013"),
    BOARD_GAME("보드게임", "B014"),
    PHOTO("사진촬영", "B015"),
    WALK("산책", "B016"),
    SHOPPING("쇼핑", "B017"),
    SKI("스키/스노우보드", "B018"),
    INSTRUMENT("악기연주", "B019"),
    ANIMATION("애니메이션", "B020"),
    MOVIE("연극/영화", "B021"),
    WINE("와인", "B022"),
    HEALTH("운동/헬스", "B023"),
    YOGA("요가/필라테스", "B024"),
    COOK("요리", "B025"),
    CARTOON("웹툰/만화", "B026"),
    INTERIOR("인테리어", "B027"),
    BICYCLE("자전거", "B028"),
    CAMP("캠핑", "B029");

    private static final int MAX_SIZE = 3;

    private final String name;
    private final String code;

    Hobby(final String name, final String code) {
        this.name = name;
        this.code = code;
    }

    public static List<Hobby> findAllByCodes(final List<String> codes) {
        validateCodes(codes);
        return codes.stream()
                .map(Hobby::findByCode)
                .toList();
    }

    private static void validateCodes(final List<String> codes) {
        validateIsNotEmptyCodes(codes);
        validateIsUniqueCodes(codes);
        validateIsNotOverMaxSizeOfHobby(codes);
    }

    private static void validateIsNotEmptyCodes(final List<String> codes) {
        if (codes.isEmpty()) {
            throw new HobbySizeException();
        }
    }

    private static void validateIsUniqueCodes(final List<String> codes) {
        Set<String> uniqueCodes = new HashSet<>(codes);
        if (uniqueCodes.size() != codes.size()) {
            throw new HobbyDuplicateException();
        }
    }

    private static void validateIsNotOverMaxSizeOfHobby(final List<String> codes) {
        int validSize = Arrays.stream(values())
                .filter(hobby -> codes.contains(hobby.getCode()))
                .toList()
                .size();
        if (validSize > MAX_SIZE) {
            throw new HobbySizeException();
        }
    }

    private static Hobby findByCode(final String code) {
        return Arrays.stream(values())
                .filter(hobby -> hobby.isSameCode(code))
                .findFirst()
                .orElseThrow(InvalidHobbyException::new);
    }

    private boolean isSameCode(final String code) {
        return code.equals(this.code);
    }
}
