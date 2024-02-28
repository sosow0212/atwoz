package com.atwoz.member.domain.info.profile;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import com.atwoz.member.exception.exceptions.info.profile.JobInvalidException;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class JobTest {

    @Test
    void 올바른_선택_시_조회된다() {
        // given
        String jobCode = "A001";

        // when & then
        assertDoesNotThrow(() -> Job.findByCode(jobCode));
    }

    @Test
    void 올바른_직업이_아니면_예외가_발생한다() {
        // given
        String jobCode = "hello";

        // when & then
        assertThatThrownBy(() -> Job.findByCode(jobCode))
                .isInstanceOf(JobInvalidException.class);
    }
}
