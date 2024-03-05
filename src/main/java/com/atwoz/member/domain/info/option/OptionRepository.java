package com.atwoz.member.domain.info.option;

import java.util.Optional;

public interface OptionRepository {

    void save(final Option option);
    Optional<Option> findByMemberId(final Long memberId);
    boolean isExistMemberOption(final Long memberId);
}
