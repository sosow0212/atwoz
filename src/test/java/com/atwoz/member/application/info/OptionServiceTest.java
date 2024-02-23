package com.atwoz.member.application.info;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import com.atwoz.member.application.info.dto.option.OptionWriteRequest;
import com.atwoz.member.application.info.option.OptionFactory;
import com.atwoz.member.application.info.option.OptionService;
import com.atwoz.member.domain.info.option.Option;
import com.atwoz.member.domain.info.option.OptionRepository;
import com.atwoz.member.exception.exceptions.info.option.OptionNotFoundException;
import com.atwoz.member.infrastructure.info.OptionFakeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class OptionServiceTest {

    private OptionService optionService;
    private OptionRepository optionRepository;
    private OptionFactory optionFactory;

    @BeforeEach
    void init() {
        optionFactory = new OptionFactory();
        optionRepository = new OptionFakeRepository();
        optionService = new OptionService(optionRepository, optionFactory);
    }

    @Test
    void option을_저장한다() {
        // given
        Long memberId = 1L;
        String drink = "전혀 마시지 않음";
        String graduate = "서울 4년제";
        String religion = "기독교";
        String smoke = "비흡연";
        String mbti = "INFJ";

        OptionWriteRequest request = new OptionWriteRequest(drink, graduate, religion, smoke, mbti);
        Option expectedOption = optionFactory.fromRequest(memberId, request);

        // when
        optionService.writeOption(memberId, request);

        // then
        assertSoftly(softly -> {
            softly.assertThat(optionRepository.isExistMemberOption(memberId)).isTrue();
            softly.assertThat(optionRepository.findByMemberId(memberId)).isPresent();
            softly.assertThat(optionRepository.findByMemberId(memberId).get()).isEqualTo(expectedOption);
        });
    }

    @Test
    void option을_조회한다() {
        // given
        Long memberId = 1L;
        String drink = "전혀 마시지 않음";
        String graduate = "서울 4년제";
        String religion = "기독교";
        String smoke = "비흡연";
        String mbti = "INFJ";

        OptionWriteRequest request = new OptionWriteRequest(drink, graduate, religion, smoke, mbti);
        
        optionService.writeOption(memberId, request);
        Option expectedOption = optionFactory.fromRequest(memberId, request);

        // when
        Option memberOption = optionService.findByMemberId(memberId);

        // then
        assertThat(memberOption).isEqualTo(expectedOption);
    }

    @Test
    void 처음에는_option이_존재하지_않는다() {
        // given
        Long memberId = 1L;

        // when & then
        assertThatThrownBy(() -> optionService.findByMemberId(memberId))
                .isInstanceOf(OptionNotFoundException.class);
    }
}
