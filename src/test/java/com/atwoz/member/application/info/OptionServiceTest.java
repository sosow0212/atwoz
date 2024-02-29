package com.atwoz.member.application.info;

import static com.atwoz.member.fixture.info.dto.request.OptionUpdateRequestFixture.옵션_수정_요청;
import static com.atwoz.member.fixture.info.dto.request.OptionWriteRequestFixture.옵션_생성_요청;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import com.atwoz.member.application.info.dto.option.OptionUpdateRequest;
import com.atwoz.member.application.info.dto.option.OptionWriteRequest;
import com.atwoz.member.application.info.option.OptionFactory;
import com.atwoz.member.application.info.option.OptionService;
import com.atwoz.member.domain.info.option.Option;
import com.atwoz.member.domain.info.option.OptionRepository;
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

    @BeforeEach
    void init() {
        optionRepository = new OptionFakeRepository();
        optionService = new OptionService(optionRepository);
    }

    @Test
    void option을_저장한다() {
        // given
        Long memberId = 1L;

        OptionWriteRequest writeRequest = 옵션_생성_요청();
        Option expectedOption = OptionFactory.createOption(memberId, writeRequest);

        // when
        optionService.writeOption(memberId, writeRequest);

        // then
        assertSoftly(softly -> {
            softly.assertThat(optionRepository.isExistMemberOption(memberId)).isTrue();
            softly.assertThat(optionRepository.findByMemberId(memberId).get())
                    .usingRecursiveComparison()
                    .ignoringFields("id")
                    .isEqualTo(expectedOption);
        });
    }

    @Test
    void option을_수정한다() {
        // given
        Long memberId = 1L;

        OptionUpdateRequest updateRequest = 옵션_수정_요청();
        Option exprectedOption = OptionFactory.createOption(memberId, updateRequest);

        OptionWriteRequest writeRequest = 옵션_생성_요청();
        optionService.writeOption(memberId, writeRequest);

        // when
        optionService.updateOption(memberId, updateRequest);

        // then
        assertThat(optionRepository.findByMemberId(memberId).get())
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(exprectedOption);
    }
}
