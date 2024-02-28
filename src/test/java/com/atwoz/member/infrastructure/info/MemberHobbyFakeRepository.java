package com.atwoz.member.infrastructure.info;

import com.atwoz.member.domain.info.hobby.MemberHobby;
import com.atwoz.member.domain.info.hobby.MemberHobbyRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberHobbyFakeRepository implements MemberHobbyRepository {

    private final Map<Long, MemberHobby> map = new HashMap<>();
    private long id = 1L;

    @Override
    public void save(final MemberHobby memberHobby) {
        MemberHobby newMemberHobby = MemberHobby.builder()
                .id(id)
                .memberId(memberHobby.getMemberId())
                .hobby(memberHobby.getHobby())
                .build();
        map.put(id, newMemberHobby);
        id++;
    }

    @Override
    public void saveAll(final List<MemberHobby> memberHobbies) {
        memberHobbies.stream()
                .map(memberHobby -> new MemberHobby(memberHobby.getMemberId(), memberHobby.getHobby()))
                .forEach(memberHobby -> map.put(id++, memberHobby));
    }

    @Override
    public void deleteHobbiesByMemberId(final Long memberId) {
        map.values().removeIf(memberHobby -> memberId.equals(memberHobby.getMemberId()));
    }

    @Override
    public List<MemberHobby> findAllByMemberId(final Long memberId) {
        return map.values()
                .stream()
                .filter(memberHobby -> memberId.equals(memberHobby.getMemberId()))
                .toList();
    }
}
