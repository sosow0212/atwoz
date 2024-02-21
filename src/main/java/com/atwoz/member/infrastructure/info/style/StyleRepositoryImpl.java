package com.atwoz.member.infrastructure.info.style;

import com.atwoz.member.domain.info.style.Style;
import com.atwoz.member.domain.info.style.StyleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class StyleRepositoryImpl implements StyleRepository {

    private final StyleJpaRepository styleJpaRepository;

    @Override
    public void save(final Style style) {
        styleJpaRepository.save(style);
    }

    @Override
    public void saveAll(final List<Style> styles) {
        styleJpaRepository.saveAll(styles);
    }

    @Override
    public void deleteStylesByMemberId(final Long memberId) {
        styleJpaRepository.deleteByMemberId(memberId);
    }
}
