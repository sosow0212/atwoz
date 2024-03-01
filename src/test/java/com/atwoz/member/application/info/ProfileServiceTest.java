package com.atwoz.member.application.info;

import static com.atwoz.member.fixture.info.dto.request.ProfileUpdateRequestFixture.프로필_수정_요청;
import static com.atwoz.member.fixture.info.dto.request.ProfileWriteRequestFixture.프로필_생성_요청;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import com.atwoz.member.application.info.dto.profile.ProfileUpdateRequest;
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
    void 프로필을_저장한다() {
        // given
        Long memberId = 1L;
        ProfileWriteRequest request = 프로필_생성_요청();

        Profile expectedProfile = ProfileFactory.createNewProfile(memberId, request, yearManager);

        // when
        profileService.writeProfile(memberId, request);

        // then
        assertSoftly(softly -> {
            softly.assertThat(profileRepository.findByMemberId(memberId)).isPresent();
            softly.assertThat(profileRepository.findByMemberId(memberId).get())
                    .usingRecursiveComparison()
                    .ignoringFields("id")
                    .isEqualTo(expectedProfile);
        });
    }

    @Test
    void 프로필을_수정한다() {
        // given
        Long memberId = 1L;

        ProfileUpdateRequest profileUpdateRequest = 프로필_수정_요청();
        Profile expectedProfile = ProfileFactory.createUpdatedProfile(memberId, profileUpdateRequest, yearManager);

        ProfileWriteRequest profileWriteRequest = 프로필_생성_요청();
        profileService.writeProfile(memberId, profileWriteRequest);

        // when
        profileService.updateProfile(memberId, profileUpdateRequest);

        // then
        assertThat(profileRepository.findByMemberId(memberId).get())
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(expectedProfile);
    }

    @Test
    void 프로필이_없을_때_수정을_시도하면_예외가_발생한다() {
        // given
        Long memberId = 1L;
        ProfileUpdateRequest profileUpdateRequest = 프로필_수정_요청();

        // when & then
        assertThatThrownBy(() -> profileService.updateProfile(memberId, profileUpdateRequest))
                .isInstanceOf(ProfileNotFoundException.class);
    }
}
