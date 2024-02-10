package com.atwoz.member.domain.auth;

import com.atwoz.member.infrastructure.auth.dto.MemberInfoKeyWordRequest;
import com.atwoz.member.infrastructure.auth.dto.MemberInfoResponse;

public interface JsonMapper {

    String getValueByKey(final String json, final String key);

    MemberInfoResponse extractMemberInfoFrom(final String memberInfoResponse,
                                             final MemberInfoKeyWordRequest memberInfoKeyWordRequest);
}
