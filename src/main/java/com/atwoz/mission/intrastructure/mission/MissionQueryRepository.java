package com.atwoz.mission.intrastructure.mission;

import com.atwoz.mission.domain.mission.dto.MissionSimpleResponse;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import static com.atwoz.mission.domain.mission.QMission.mission;
import static com.querydsl.core.types.Projections.constructor;

@RequiredArgsConstructor
@Repository
public class MissionQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public Page<MissionSimpleResponse> findAllMissionsWithPaging(final Pageable pageable) {
        QueryResults<MissionSimpleResponse> result = jpaQueryFactory.select(
                        constructor(MissionSimpleResponse.class,
                                mission.id,
                                mission.title,
                                mission.missionType,
                                mission.reward,
                                mission.publicOption,
                                mission.createdAt
                        )).from(mission)
                .orderBy(mission.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        return new PageImpl<>(result.getResults(), pageable, result.getTotal());
    }
}
