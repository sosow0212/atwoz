package com.atwoz.member.application.info;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
@ExtendWith(MockitoExtension.class)
public class OptionServiceTest {

    private OptionService optionService;
    private OptionRepository optionRepository;

    @BeforeEach
    void init() {
        optionRepository = new OptionFakeRepository();
        optionService = new OptionService(optionRepository);
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

        // when
        optionService.writeOption(memberId, request);

        // then
        Optional<Option> memberOption = optionRepository.findByMemberId(memberId);
        assertThat(memberOption).isPresent();
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
        Option expectedOption = OptionFactory.fromRequest(memberId, request);
        optionService.writeOption(memberId, request);

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
