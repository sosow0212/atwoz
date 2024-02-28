package com.atwoz.member.application.info.hobby;

import com.atwoz.member.domain.info.hobby.Hobby;
import com.atwoz.member.domain.info.hobby.MemberHobby;
import com.atwoz.member.domain.info.hobby.MemberHobbyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberHobbyService {

    private final MemberHobbyRepository memberHobbyRepository;

    @Transactional
    public void saveMemberHobbies(final Long memberId, final List<String> hobbyCodes) {
        saveMemberHobbiesByHobbyCodes(memberId, hobbyCodes);
    }

    private void saveMemberHobbiesByHobbyCodes(final Long memberId, final List<String> hobbyCodes) {
        List<MemberHobby> memberHobbies = Hobby.findAllByCodes(hobbyCodes)
                .stream()
                .map(hobby -> new MemberHobby(memberId, hobby))
                .toList();

        memberHobbyRepository.saveAll(memberHobbies);
    }

    @Transactional
    public void updateMemberHobbies(final Long memberId, final List<String> hobbyCodes) {
        memberHobbyRepository.deleteHobbiesByMemberId(memberId);

        saveMemberHobbiesByHobbyCodes(memberId, hobbyCodes);
    }
}
