package com.atwoz.member.domain.info.hobby;

import java.util.List;

public interface MemberHobbyRepository {

    void save(final MemberHobby memberHobby);
    void saveAll(final List<MemberHobby> memberHobbies);
    void deleteHobbiesByMemberId(final Long memberId);
    List<MemberHobby> findAllByMemberId(final Long memberId);
}
