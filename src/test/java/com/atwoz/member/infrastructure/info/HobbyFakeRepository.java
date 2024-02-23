package com.atwoz.member.infrastructure.info;

import com.atwoz.member.domain.info.hobby.Hobby;
import com.atwoz.member.domain.info.hobby.HobbyRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HobbyFakeRepository implements HobbyRepository {

    private final Map<Long, Hobby> map = new HashMap<>();
    private long id = 1L;

    @Override
    public void save(final Hobby hobby) {
        Hobby newHobby = new Hobby(hobby.getMemberId(), hobby.getHobbyName());
        map.put(id, newHobby);
        id++;
    }

    @Override
    public void saveAll(final List<Hobby> memberHobbies) {
        memberHobbies.stream()
                .map(hobby -> new Hobby(hobby.getMemberId(), hobby.getHobbyName()))
                .forEach(hobby -> map.put(id++, hobby));
    }

    @Override
    public void deleteHobbiesByMemberId(final Long memberId) {
        map.values().removeIf(hobby -> memberId.equals(hobby.getMemberId()));
    }

    @Override
    public List<Hobby> findAllByMemberId(final Long memberId) {
        return map.values()
                .stream()
                .filter(hobby -> memberId.equals(hobby.getMemberId()))
                .toList();
    }
}
