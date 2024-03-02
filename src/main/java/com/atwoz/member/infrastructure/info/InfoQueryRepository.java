package com.atwoz.member.infrastructure.info;

import static com.atwoz.member.domain.info.hobby.QMemberHobby.memberHobby;
import static com.atwoz.member.domain.info.option.QOption.option;
import static com.atwoz.member.domain.info.profile.QProfile.profile;
import static com.atwoz.member.domain.info.style.QMemberStyle.memberStyle;

import com.atwoz.member.domain.info.option.Option;
import com.atwoz.member.domain.info.profile.Profile;
import com.atwoz.member.ui.info.dto.HobbySearchResponse;
import com.atwoz.member.ui.info.dto.InfoSearchResponse;
import com.atwoz.member.ui.info.dto.StyleSearchResponse;
import com.atwoz.member.ui.info.dto.option.OptionSearchResponse;
import com.atwoz.member.ui.info.dto.profile.ProfileSearchResponse;
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
        Profile profile = selectProfileByMemberId(memberId);
        if (profile == null) {
            return Optional.empty();
        }

        Option option = selectOptionByMemberId(memberId);
        if (option == null) {
            return Optional.empty();
        }

        ProfileSearchResponse profileSearchResponse = ProfileSearchResponse.from(profile);
        OptionSearchResponse optionSearchResponse = OptionSearchResponse.from(option);

        List<HobbySearchResponse> hobbyCodes = selectHobbyResponseByMemberId(memberId);
        List<StyleSearchResponse> styleCodes = selectStyleResponseByMemberId(memberId);

        return Optional.of(new InfoSearchResponse(profileSearchResponse, optionSearchResponse, hobbyCodes, styleCodes));
    }

    private Profile selectProfileByMemberId(final Long memberId) {
        return queryFactory.select(profile)
                        .from(profile)
                        .where(profile.memberId.eq(memberId))
                        .fetchOne();
    }

    private Option selectOptionByMemberId(final Long memberId) {
        return queryFactory.select(option)
                        .from(option)
                        .where(option.memberId.eq(memberId))
                        .fetchOne();
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
