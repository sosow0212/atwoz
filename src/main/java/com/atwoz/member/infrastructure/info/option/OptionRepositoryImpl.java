package com.atwoz.member.infrastructure.info.option;

import com.atwoz.member.domain.info.option.Option;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class OptionRepositoryImpl implements OptionRepository{

    private final OptionJpaRepository optionJpaRepository;

    @Override
    public void save(final Option option) {
        optionJpaRepository.save(option);
    }
}
