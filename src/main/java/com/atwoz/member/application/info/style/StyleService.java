package com.atwoz.member.application.info.style;

import com.atwoz.member.domain.info.style.MemberStyle;
import com.atwoz.member.domain.info.style.Style;
import com.atwoz.member.domain.info.style.MemberStyleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class StyleService {

    private final MemberStyleRepository memberStyleRepository;

    @Transactional
    public void saveMemberStyles(final Long memberId, final List<String> styleCodes) {
        saveMemberStylesByStyleCodes(memberId, styleCodes);
    }

    private void saveMemberStylesByStyleCodes(final Long memberId, final List<String> styleCodes) {
        List<MemberStyle> memberStyles = Style.findAllByCodes(styleCodes)
                .stream()
                .map(style -> new MemberStyle(memberId, style))
                .toList();

        memberStyleRepository.saveAll(memberStyles);
    }

    @Transactional
    public void updateMemberStyles(final Long memberId, final List<String> styleCodes) {
        memberStyleRepository.deleteStylesByMemberId(memberId);

        saveMemberStylesByStyleCodes(memberId, styleCodes);
    }
}
