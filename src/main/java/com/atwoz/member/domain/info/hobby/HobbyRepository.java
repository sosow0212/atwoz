package com.atwoz.member.domain.info.hobby;

import java.util.List;

public interface HobbyRepository {

    void save(final Hobby hobby);
    void saveAll(final List<Hobby> memberHobbies);
    void deleteHobbiesByMemberId(final Long memberId);
}
