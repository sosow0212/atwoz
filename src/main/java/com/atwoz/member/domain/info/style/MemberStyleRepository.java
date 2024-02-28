package com.atwoz.member.domain.info.style;

import java.util.List;

public interface MemberStyleRepository {

    void save(final MemberStyle style);
    void saveAll(final List<MemberStyle> styles);
    void deleteStylesByMemberId(final Long memberId);
    List<MemberStyle> findAllByMemberId(Long memberId);
}
