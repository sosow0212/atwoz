package com.atwoz.member.infrastructure.info;

import static com.atwoz.member.domain.info.hobby.QMemberHobby.memberHobby;
import static com.atwoz.member.domain.info.option.QOption.option;
import static com.atwoz.member.domain.info.profile.QProfile.profile;
import static com.atwoz.member.domain.info.style.QMemberStyle.memberStyle;

import com.atwoz.member.ui.info.dto.HobbySearchResponse;
import com.atwoz.member.ui.info.dto.InfoSearchResponse;
import com.atwoz.member.ui.info.dto.ProfileAndOptionSearchResponse;
import com.atwoz.member.ui.info.dto.StyleSearchResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class InfoQueryRepository {

    private final JPAQueryFactory queryFactory;

    public Optional<InfoSearchResponse> findByMemberId(final Long memberId) {
        Optional<ProfileAndOptionSearchResponse> profileAndOptionSearchResponse = Optional.ofNullable(queryFactory
                .select(Projections.fields(ProfileAndOptionSearchResponse.class,
                        profile.body.age.as("age"),
                        profile.body.height.as("height"),
                        profile.body.gender.as("gender"),
                        profile.job.as("job"),
                        profile.location.city.as("city"),
                        profile.location.sector.as("sector"),
                        profile.position.latitude.as("latitude"),
                        profile.position.longitude.as("longitude"),
                        option.drink.as("drink"),
                        option.graduate.as("graduate"),
                        option.mbti.as("mbti"),
                        option.religion.as("religion"),
                        option.smoke.as("smoke")))
                .from(profile)
                .join(option)
                .on(profile.memberId.eq(option.memberId))
                .where(profile.memberId.eq(memberId))
                .fetchOne());

        if (profileAndOptionSearchResponse.isEmpty()) {
            return Optional.empty();
        }

        List<HobbySearchResponse> hobbyCodes = selectHobbyResponseByMemberId(memberId);
        List<StyleSearchResponse> styleCodes = selectStyleResponseByMemberId(memberId);

        return Optional.of(InfoSearchResponse.of(profileAndOptionSearchResponse.get(), hobbyCodes, styleCodes));
    }

    private List<HobbySearchResponse> selectHobbyResponseByMemberId(final Long memberId) {
        return queryFactory.select(memberHobby)
                .from(memberHobby)
                .where(memberHobby.memberId.eq(memberId))
                .fetch()
                .stream()
                .map(HobbySearchResponse::from)
                .toList();
    }

    private List<StyleSearchResponse> selectStyleResponseByMemberId(final Long memberId) {
        return queryFactory.select(memberStyle)
                .from(memberStyle)
                .where(memberStyle.memberId.eq(memberId))
                .fetch()
                .stream()
                .map(StyleSearchResponse::from)
                .toList();
    }
}
