package com.atwoz.member.application.info.hobby;

import com.atwoz.member.domain.info.hobby.Hobby;
import com.atwoz.member.domain.info.hobby.HobbyName;
import com.atwoz.member.domain.info.hobby.HobbyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class HobbyService {

    private final HobbyRepository hobbyRepository;

    @Transactional
    public void saveMemberHobbies(final Long memberId, final List<String> hobbyNames) {
        deleteMemberHobbies(memberId);
        List<Hobby> memberHobbies = HobbyName.findAllByCodes(hobbyNames)
                .stream()
                .map(hobbyName -> new Hobby(memberId, hobbyName))
                .toList();

        hobbyRepository.saveAll(memberHobbies);
    }

    @Transactional
    public void deleteMemberHobbies(final Long memberId) {
        hobbyRepository.deleteHobbiesByMemberId(memberId);
    }

    public List<Hobby> findMemberHobbies(final Long memberId) {
        return hobbyRepository.findAllByMemberId(memberId);
    }
}
