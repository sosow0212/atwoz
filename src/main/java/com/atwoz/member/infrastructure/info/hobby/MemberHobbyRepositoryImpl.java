package com.atwoz.member.infrastructure.info.hobby;

import com.atwoz.member.domain.info.hobby.MemberHobby;
import com.atwoz.member.domain.info.hobby.MemberHobbyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class MemberHobbyRepositoryImpl implements MemberHobbyRepository {

    private final HobbyJpaRepository hobbyJpaRepository;

    @Override
    public void save(final MemberHobby memberHobby) {
        hobbyJpaRepository.save(memberHobby);
    }

    @Override
    public void saveAll(final List<MemberHobby> memberHobbies) {
        hobbyJpaRepository.saveAll(memberHobbies);
    }

    @Override
    public void deleteHobbiesByMemberId(final Long memberId) {
        hobbyJpaRepository.deleteByMemberId(memberId);
    }

    @Override
    public List<MemberHobby> findAllByMemberId(final Long memberId) {
        return hobbyJpaRepository.findAllByMemberId(memberId);
    }
}
