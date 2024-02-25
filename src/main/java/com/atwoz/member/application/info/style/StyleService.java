package com.atwoz.member.application.info.style;

import com.atwoz.member.domain.info.style.Style;
import com.atwoz.member.domain.info.style.StyleName;
import com.atwoz.member.domain.info.style.StyleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class StyleService {

    private final StyleRepository styleRepository;

    @Transactional
    public void saveMemberStyles(final Long memberId, final List<String> styleNames) {
        deleteMemberStyles(memberId);
        List<Style> memberStyles = StyleName.findAllByCodes(styleNames)
                .stream()
                .map(styleName -> new Style(memberId, styleName))
                .toList();

        styleRepository.saveAll(memberStyles);
    }

    @Transactional
    public void deleteMemberStyles(final Long memberId) {
        styleRepository.deleteStylesByMemberId(memberId);
    }

    public List<Style> findMemberStyles(final Long memberId) {
        return styleRepository.findAllByMemberId(memberId);
    }
}
