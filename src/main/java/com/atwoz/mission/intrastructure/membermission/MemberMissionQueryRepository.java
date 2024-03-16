package com.atwoz.mission.intrastructure.membermission;

import com.atwoz.mission.intrastructure.membermission.dto.MemberMissionSimpleResponse;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import static com.atwoz.mission.domain.membermission.QMemberMission.memberMission;
import static com.atwoz.mission.domain.membermission.QMemberMissions.memberMissions1;
import static com.querydsl.core.types.Projections.constructor;

@RequiredArgsConstructor
@Repository
public class MemberMissionQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public Page<MemberMissionSimpleResponse> findMemberMissionsWithPaging(final Long memberId, final Pageable pageable) {
        QueryResults<MemberMissionSimpleResponse> result = jpaQueryFactory.select(
                        constructor(MemberMissionSimpleResponse.class,
                                memberMission.mission.id,
                                memberMission.doesGetReward,
                                memberMission.isStatusClear,
                                memberMission.mission.reward)
                ).from(memberMission)
                .innerJoin(memberMissions1)
                .on(memberMissions1.memberMissions.contains(memberMission))
                .where(memberMissions1.memberId.eq(memberId))
                .orderBy(memberMission.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        return new PageImpl<>(result.getResults(), pageable, result.getTotal());
    }

    public List<MemberMissionSimpleResponse> findMemberMissionsByStatus(final Long memberId, final boolean isStatusClear) {
        return jpaQueryFactory.select(
                        constructor(MemberMissionSimpleResponse.class,
                                memberMission.mission.id,
                                memberMission.doesGetReward,
                                memberMission.isStatusClear,
                                memberMission.mission.reward)
                ).from(memberMission)
                .innerJoin(memberMissions1)
                .on(memberMissions1.memberMissions.contains(memberMission))
                .where(memberMissions1.memberId.eq(memberId))
                .where(memberMission.isStatusClear.eq(isStatusClear))
                .orderBy(memberMission.id.desc())
                .fetch();
    }
}
