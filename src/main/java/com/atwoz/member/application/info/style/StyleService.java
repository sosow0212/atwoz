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
    public void saveMemberStyles(final Long memberId, final List<String> styleNames) {
        deleteMemberStyles(memberId);
        List<MemberStyle> memberStyles = Style.findAllByCodes(styleNames)
                .stream()
                .map(style -> new MemberStyle(memberId, style))
                .toList();

        memberStyleRepository.saveAll(memberStyles);
    }

    @Transactional
    public void deleteMemberStyles(final Long memberId) {
        memberStyleRepository.deleteStylesByMemberId(memberId);
    }

    public List<MemberStyle> findMemberStyles(final Long memberId) {
        return memberStyleRepository.findAllByMemberId(memberId);
    }
}
