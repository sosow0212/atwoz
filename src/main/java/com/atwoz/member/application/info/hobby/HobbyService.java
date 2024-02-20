package com.atwoz.member.application.info.hobby;

import com.atwoz.member.domain.info.hobby.Hobbies;
import com.atwoz.member.infrastructure.info.hobby.HobbiesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class HobbyService {

    private final HobbiesRepository hobbiesRepository;

    @Transactional
    public void writeHobbies(final Long memberId, final List<String> hobbies) {

        hobbiesRepository.save(new Hobbies(memberId, hobbies));
    }
}
