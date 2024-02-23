package com.atwoz.member.application.info.option;

import com.atwoz.member.application.info.dto.option.OptionWriteRequest;
import com.atwoz.member.domain.info.option.Option;
import com.atwoz.member.domain.info.option.OptionRepository;
import com.atwoz.member.exception.exceptions.info.option.OptionNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class OptionService {

    private final OptionRepository optionRepository;

    @Transactional
    public void writeOption(final Long memberId, final OptionWriteRequest request) {
        Option newOption = OptionFactory.fromRequest(memberId, request);
        if (!isMemberOptionExist(memberId)) {
            optionRepository.save(newOption);
            return;
        }
        Option existOption = findByMemberId(memberId);
        existOption.updateContents(
                newOption.getSmoke(),
                newOption.getReligion(),
                newOption.getDrink(),
                newOption.getMbti(),
                newOption.getGraduate()
        );
    }

    private boolean isMemberOptionExist(final Long memberId) {
        return optionRepository.isExistMemberOption(memberId);
    }

    public Option findByMemberId(final Long memberId) {
        return optionRepository.findByMemberId(memberId)
                .orElseThrow(OptionNotFoundException::new);
    }
}
