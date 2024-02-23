package com.atwoz.member.application.info;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import com.atwoz.member.application.info.dto.profile.LocationWriteRequest;
import com.atwoz.member.application.info.dto.profile.PositionWriteRequest;
import com.atwoz.member.application.info.dto.profile.ProfileWriteRequest;
import com.atwoz.member.application.info.profile.ProfileFactory;
import com.atwoz.member.application.info.profile.ProfileService;
import com.atwoz.member.domain.info.profile.Profile;
import com.atwoz.member.domain.info.profile.ProfileRepository;
import com.atwoz.member.domain.info.profile.year.YearManager;
import com.atwoz.member.exception.exceptions.info.profile.ProfileNotFoundException;
import com.atwoz.member.infrastructure.info.ProfileFakeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
@ExtendWith(MockitoExtension.class)
public class ProfileServiceTest {

    private ProfileService profileService;
    private ProfileRepository profileRepository;
    private ProfileFactory profileFactory;
    private YearManager yearManager;

    @BeforeEach
    void init() {
        profileRepository = new ProfileFakeRepository();
        yearManager = new FakeYearManager();
        profileFactory = new ProfileFactory(yearManager);
        profileService = new ProfileService(profileRepository, profileFactory);
    }

    @Test
    void profile을_저장한다() {
        // given
        Long memberId = 1L;
        int birthYear = 2000;
        int height = 171;
        String gender = "남성";
        double latitude = 70.5;
        double longitude = 140.3;
        String job = "개발자";
        LocationWriteRequest location = new LocationWriteRequest("서울시", "강남구");
        PositionWriteRequest position = new PositionWriteRequest(latitude, longitude);
        ProfileWriteRequest request = new ProfileWriteRequest(birthYear, height, gender, location, position, job);

        Profile expectedProfile = profileFactory.fromRequest(memberId, request);

        // when
        profileService.writeProfile(memberId, request);

        // then
        assertSoftly(softly -> {
            softly.assertThat(profileRepository.isExistMemberProfile(memberId)).isTrue();
            softly.assertThat(profileRepository.findByMemberId(memberId)).isPresent();
            softly.assertThat(profileRepository.findByMemberId(memberId).get()).isEqualTo(expectedProfile);
        });
    }

    @Test
    void profile을_조회한다() {
        // given
        Long memberId = 1L;
        int birthYear = 2000;
        int height = 171;
        String gender = "남성";
        double latitude = 70.5;
        double longitude = 140.3;
        String job = "개발자";
        LocationWriteRequest location = new LocationWriteRequest("서울시", "강남구");
        PositionWriteRequest position = new PositionWriteRequest(latitude, longitude);
        ProfileWriteRequest request = new ProfileWriteRequest(birthYear, height, gender, location, position, job);

        profileService.writeProfile(memberId, request);
        Profile expectedProfile = profileFactory.fromRequest(memberId, request);

        // when
        Profile findProfile = profileService.findByMemberId(memberId);

        // then
        assertThat(findProfile).isEqualTo(expectedProfile);
    }

    @Test
    void 처음에는_profile이_존재하지_않는다() {
        // given
        Long memberId = 1L;

        // when & then
        assertThatThrownBy(() -> profileService.findByMemberId(memberId))
                .isInstanceOf(ProfileNotFoundException.class);
    }
}
