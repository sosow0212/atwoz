package com.atwoz.member.domain.info;

import com.atwoz.member.ui.info.dto.InfoSearchResponse;
import java.util.Optional;

public interface InfoRepository {

    Optional<InfoSearchResponse> findByMemberId(final Long memberId);
}
