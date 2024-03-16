package com.atwoz.mission.intrastructure.membermission.dto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public record MemberMissionPagingResponse(
        List<MemberMissionSimpleResponse> memberMissions,
        int nextPage
) {

    private static final int NEXT_PAGE_INDEX = 1;
    private static final int NO_MORE_PAGE = -1;

    public static MemberMissionPagingResponse of(final Page<MemberMissionSimpleResponse> memberMissions, final Pageable pageable) {
        return new MemberMissionPagingResponse(memberMissions.getContent(), getNextPage(pageable.getPageNumber(), memberMissions));
    }

    private static int getNextPage(final int pageNumber, final Page<MemberMissionSimpleResponse> memberMissions) {
        if (memberMissions.hasNext()) {
            return pageNumber + NEXT_PAGE_INDEX;
        }

        return NO_MORE_PAGE;
    }
}
