package com.atwoz.member.application.info.style;

import com.atwoz.member.domain.info.style.Styles;
import com.atwoz.member.infrastructure.info.style.StylesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class StyleService {

    private final StylesRepository stylesRepository;

    @Transactional
    public void writeStyles(final Long memberId, final List<String> styles) {
        stylesRepository.save(new Styles(memberId, styles));
    }
}
