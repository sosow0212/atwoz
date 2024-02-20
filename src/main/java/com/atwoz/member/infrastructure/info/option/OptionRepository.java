package com.atwoz.member.infrastructure.info.option;

import com.atwoz.member.domain.info.option.Option;
import java.util.Optional;

public interface OptionRepository {

    void save(final Option option);
    Optional<Option> findByMemberId(final Long memberId);
}
