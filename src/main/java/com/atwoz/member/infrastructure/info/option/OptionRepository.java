package com.atwoz.member.infrastructure.info.option;

import com.atwoz.member.domain.info.option.Option;

public interface OptionRepository {

    void save(final Option option);
}
