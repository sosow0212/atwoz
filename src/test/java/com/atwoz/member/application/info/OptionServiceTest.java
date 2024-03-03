package com.atwoz.member.application.info;

import static com.atwoz.member.fixture.domain.info.option.OptionFixture.회원_수정_옵션_생성;
import static com.atwoz.member.fixture.domain.info.option.OptionFixture.회원_일반_옵션_생성;
import static com.atwoz.member.fixture.application.info.option.OptionRequestFixture.회원_옵션_생성_요청;
import static com.atwoz.member.fixture.application.info.option.OptionRequestFixture.회원_옵션_수정_요청;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import com.atwoz.member.application.info.option.dto.OptionUpdateRequest;
import com.atwoz.member.application.info.option.dto.OptionWriteRequest;
import com.atwoz.member.application.info.option.OptionService;
import com.atwoz.member.domain.info.option.Option;
import com.atwoz.member.domain.info.option.OptionRepository;
import com.atwoz.member.domain.info.option.dto.InnerOptionWriteRequest;
import com.atwoz.member.exception.exceptions.info.option.OptionNotFoundException;
import com.atwoz.member.infrastructure.info.option.OptionFakeRepository;
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
    void 옵션을_저장한다() {
        // given
        Long memberId = 1L;

        OptionWriteRequest writeRequest = 회원_옵션_생성_요청();
        InnerOptionWriteRequest innerOptionWriteRequest = InnerOptionWriteRequest.of(memberId, writeRequest);
        Option expectedOption = Option.createFrom(innerOptionWriteRequest);

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
    void 옵션을_수정한다() {
        // given
        Long memberId = 1L;
        Option option = 회원_일반_옵션_생성();
        optionRepository.save(option);

        Option expectedOption = 회원_수정_옵션_생성();
        OptionUpdateRequest updateRequest = 회원_옵션_수정_요청();

        // when
        optionService.updateOption(memberId, updateRequest);

        // then
        assertThat(optionRepository.findByMemberId(memberId).get())
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(expectedOption);
    }

    @Test
    void 옵션이_없을_때_수정을_시도하면_예외가_발생한다() {
        // given
        Long memberId = 1L;
        OptionUpdateRequest updateRequest = 회원_옵션_수정_요청();

        // when & then
        assertThatThrownBy(() -> optionService.updateOption(memberId, updateRequest))
                .isInstanceOf(OptionNotFoundException.class);
    }
}
