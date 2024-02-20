package com.atwoz.member.application.info.option;

import com.atwoz.member.application.info.dto.option.OptionWriteRequest;
import com.atwoz.member.domain.info.option.Option;
import com.atwoz.member.infrastructure.info.option.OptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class OptionService {

    private final OptionFactory optionFactory;
    private final OptionRepository optionRepository;

    @Transactional
    public void writeOption(final Long memberId, final OptionWriteRequest request) {
        Optional<Option> findOption = findByMemberId(memberId);
        Option newOption = optionFactory.fromRequest(memberId, request);
        if (findOption.isEmpty()) {
            optionRepository.save(newOption);
            return;
        }
        Option existOption = findOption.get();
        existOption.updateContents(
                existOption.getSmoke(),
                existOption.getReligion(),
                existOption.getDrink(),
                existOption.getMbti(),
                existOption.getGraduate()
        );
    }

    private Optional<Option> findByMemberId(final Long memberId) {
        return optionRepository.findByMemberId(memberId);
    }
}
