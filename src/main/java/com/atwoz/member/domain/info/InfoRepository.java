package com.atwoz.member.domain.info;

import com.atwoz.member.ui.info.dto.InfoSearchResponse;

public interface InfoRepository {

    InfoSearchResponse findByMemberId(final Long memberId);
}
