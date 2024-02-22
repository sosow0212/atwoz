package com.atwoz.member.infrastructure.info.hobby;

import com.atwoz.member.domain.info.hobby.Hobby;
import com.atwoz.member.domain.info.hobby.HobbyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class HobbyRepositoryImpl implements HobbyRepository {

    private final HobbyJpaRepository hobbyJpaRepository;

    @Override
    public void save(final Hobby hobby) {
        hobbyJpaRepository.save(hobby);
    }

    @Override
    public void saveAll(final List<Hobby> memberHobbies) {
        hobbyJpaRepository.saveAll(memberHobbies);
    }

    @Override
    public void deleteHobbiesByMemberId(final Long memberId) {
        hobbyJpaRepository.deleteByMemberId(memberId);
    }

    @Override
    public List<Hobby> findAllByMemberId(final Long memberId) {
        return hobbyJpaRepository.findAllByMemberId(memberId);
    }
}
