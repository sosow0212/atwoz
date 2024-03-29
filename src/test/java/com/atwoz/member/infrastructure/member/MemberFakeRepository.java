package com.atwoz.member.infrastructure.member;

import com.atwoz.member.domain.member.Member;
import com.atwoz.member.domain.member.MemberRepository;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MemberFakeRepository implements MemberRepository {

    private final Map<Long, Member> map = new HashMap<>();
    private Long id = 0L;

    @Override
    public Optional<Member> findById(final Long id) {
        return Optional.of(map.get(id));
    }

    @Override
    public Optional<Member> findByNickname(final String nickname) {
        return map.values().stream()
                .filter(member -> member.getNickname().equals(nickname))
                .findAny();
    }

    @Override
    public Optional<Member> findByEmail(final String email) {
        return map.values().stream()
                .filter(member -> member.getEmail().equals(email))
                .findAny();
    }

    @Override
    public Member save(final Member member) {
        Member saved = Member.builder()
                .id(id)
                .email(member.getEmail())
                .nickname(member.getNickname())
                .memberRole(member.getMemberRole())
                .build();

        map.put(id, member);

        id++;
        return saved;
    }

    @Override
    public boolean existsByEmail(final String email) {
        return map.values().stream()
                .anyMatch(member -> member.getEmail().equals(email));
    }
}
