package com.atwoz.member.infrastructure.info.style;

import com.atwoz.member.domain.info.style.MemberStyle;
import com.atwoz.member.domain.info.style.MemberStyleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class MemberStyleRepositoryImpl implements MemberStyleRepository {

    private final MemberStyleJpaRepository memberStyleJpaRepository;

    @Override
    public void save(final MemberStyle style) {
        memberStyleJpaRepository.save(style);
    }

    @Override
    public void saveAll(final List<MemberStyle> styles) {
        memberStyleJpaRepository.saveAll(styles);
    }

    @Override
    public void deleteStylesByMemberId(final Long memberId) {
        memberStyleJpaRepository.deleteByMemberId(memberId);
    }

    @Override
    public List<MemberStyle> findAllByMemberId(final Long memberId) {
        return memberStyleJpaRepository.findAllByMemberId(memberId);
    }
}
