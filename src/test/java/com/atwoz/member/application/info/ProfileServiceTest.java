package com.atwoz.member.application.info;

import static com.atwoz.member.fixture.info.dto.ProfileWriteRequestFixture.프로필_생성_요청;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import com.atwoz.member.application.info.dto.profile.ProfileWriteRequest;
import com.atwoz.member.application.info.profile.ProfileFactory;
import com.atwoz.member.application.info.profile.ProfileService;
import com.atwoz.member.domain.info.profile.Profile;
import com.atwoz.member.domain.info.profile.ProfileRepository;
import com.atwoz.member.domain.info.profile.YearManager;
import com.atwoz.member.exception.exceptions.info.profile.ProfileNotFoundException;
import com.atwoz.member.infrastructure.info.FakeYearManager;
import com.atwoz.member.infrastructure.info.ProfileFakeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class ProfileServiceTest {

    private ProfileService profileService;
    private ProfileRepository profileRepository;
    private YearManager yearManager;

    @BeforeEach
    void init() {
        profileRepository = new ProfileFakeRepository();
        yearManager = new FakeYearManager();
        profileService = new ProfileService(profileRepository, yearManager);
    }

    @Test
    void profile을_저장한다() {
        // given
        Long memberId = 1L;
        ProfileWriteRequest request = 프로필_생성_요청();

        Profile expectedProfile = ProfileFactory.of(memberId, request, yearManager);

        // when
        profileService.writeProfile(memberId, request);

        // then
        assertSoftly(softly -> {
            softly.assertThat(profileRepository.findByMemberId(memberId)).isPresent();
            softly.assertThat(profileRepository.findByMemberId(memberId).get()).isEqualTo(expectedProfile);
        });
    }

    @Test
    void profile을_조회한다() {
        // given
        Long memberId = 1L;
        ProfileWriteRequest request = 프로필_생성_요청();

        profileService.writeProfile(memberId, request);
        Profile expectedProfile = ProfileFactory.of(memberId, request, yearManager);

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
