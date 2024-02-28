package com.atwoz.member.domain.info.style;

import java.util.List;

public interface MemberStyleRepository {

    void save(final MemberStyle memberStyle);
    void saveAll(final List<MemberStyle> memberStyles);
    void deleteStylesByMemberId(final Long memberId);
    List<MemberStyle> findAllByMemberId(Long memberId);
}
