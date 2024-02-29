package com.atwoz.member.infrastructure.info.hobby;

import com.atwoz.member.domain.info.hobby.MemberHobby;
import com.atwoz.member.domain.info.hobby.MemberHobbyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class MemberHobbyRepositoryImpl implements MemberHobbyRepository {

    private final MemberHobbyJpaRepository memberHobbyJpaRepository;

    @Override
    public void save(final MemberHobby memberHobby) {
        memberHobbyJpaRepository.save(memberHobby);
    }

    @Override
    public void saveAll(final List<MemberHobby> memberHobbies) {
        memberHobbyJpaRepository.saveAll(memberHobbies);
    }

    @Override
    public void deleteHobbiesByMemberId(final Long memberId) {
        memberHobbyJpaRepository.deleteByMemberId(memberId);
    }

    @Override
    public List<MemberHobby> findAllByMemberId(final Long memberId) {
        return memberHobbyJpaRepository.findAllByMemberId(memberId);
    }
}
