package com.atwoz.member.domain.info.style;

import java.util.List;

public interface StyleRepository {

    void save(final Style style);
    void saveAll(final List<Style> styles);
    void deleteStylesByMemberId(final Long memberId);
    List<Style> findAllByMemberId(Long memberId);
}
