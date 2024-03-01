package com.atwoz.member.infrastructure.info;

import com.atwoz.member.domain.info.InfoRepository;
import com.atwoz.member.ui.info.dto.InfoSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class InfoRepositoryImpl implements InfoRepository {

    private final InfoQueryRepository infoQueryRepository;

    @Override
    public InfoSearchResponse findByMemberId(final Long memberId) {
        return infoQueryRepository.findByMemberId(memberId);
    }
}
