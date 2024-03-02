package com.atwoz.member.application.info.option;

import com.atwoz.member.application.info.dto.option.OptionUpdateRequest;
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
        Option newOption = Option.of(
                memberId,
                request.smoke(),
                request.religion(),
                request.drink(),
                request.mbti(),
                request.graduate()
        );

        if (!optionRepository.isExistMemberOption(memberId)) {
            optionRepository.save(newOption);
        }
    }

    @Transactional
    public void updateOption(final Long memberId, final OptionUpdateRequest request) {
        Option existOption = findByMemberId(memberId);
        Option newOption = Option.of(
                memberId,
                request.smoke(),
                request.religion(),
                request.drink(),
                request.mbti(),
                request.graduate()
        );

        existOption.updateContents(
                newOption.getSmoke(),
                newOption.getReligion(),
                newOption.getDrink(),
                newOption.getMbti(),
                newOption.getGraduate()
        );
    }

    private Option findByMemberId(final Long memberId) {
        return optionRepository.findByMemberId(memberId)
                .orElseThrow(OptionNotFoundException::new);
    }
}
