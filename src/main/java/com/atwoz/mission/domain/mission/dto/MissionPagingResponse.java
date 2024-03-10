package com.atwoz.mission.domain.mission.dto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public record MissionPagingResponse(
        List<MissionSimpleResponse> missions,
        int nextPage
) {

    private static final int NEXT_PAGE_INDEX = 1;
    private static final int NO_MORE_PAGE = -1;

    public static MissionPagingResponse of(final Page<MissionSimpleResponse> missions, final Pageable pageable) {
        return new MissionPagingResponse(missions.getContent(), getNextPage(pageable.getPageNumber(), missions));
    }

    private static int getNextPage(int pageNumber, Page<MissionSimpleResponse> missions) {
        if (missions.hasNext()) {
            return pageNumber + NEXT_PAGE_INDEX;
        }

        return NO_MORE_PAGE;
    }
}
