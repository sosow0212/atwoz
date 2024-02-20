package com.atwoz.member.infrastructure.info.style;

import com.atwoz.member.domain.info.style.Styles;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class StylesRepositoryImpl implements StylesRepository {

    private final StylesJpaRepository stylesJpaRepository;

    @Override
    public void save(final Styles styles) {
        stylesJpaRepository.save(styles);
    }
}
