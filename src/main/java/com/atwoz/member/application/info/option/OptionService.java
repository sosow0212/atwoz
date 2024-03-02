package com.atwoz.member.application.info.option;

import com.atwoz.member.application.info.dto.option.OptionUpdateRequest;
import com.atwoz.member.application.info.dto.option.OptionWriteRequest;
import com.atwoz.member.domain.info.option.Option;
import com.atwoz.member.domain.info.option.OptionRepository;
import com.atwoz.member.domain.info.option.dto.InnerOptionUpdateRequest;
import com.atwoz.member.domain.info.option.dto.InnerOptionWriteRequest;
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
        InnerOptionWriteRequest optionWriteRequest = InnerOptionWriteRequest.of(memberId, request);
        Option newOption = Option.createFrom(optionWriteRequest);
        if (!optionRepository.isExistMemberOption(memberId)) {
            optionRepository.save(newOption);
        }
    }

    @Transactional
    public void updateOption(final Long memberId, final OptionUpdateRequest request) {
        InnerOptionUpdateRequest optionUpdateRequest = InnerOptionUpdateRequest.from(request);
        Option existOption = findByMemberId(memberId);
        existOption.updateContentsFrom(optionUpdateRequest);
    }

    private Option findByMemberId(final Long memberId) {
        return optionRepository.findByMemberId(memberId)
                .orElseThrow(OptionNotFoundException::new);
    }
}
