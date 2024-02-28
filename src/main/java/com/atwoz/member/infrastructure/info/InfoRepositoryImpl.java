package com.atwoz.member.infrastructure.info;

import static com.atwoz.member.domain.info.hobby.QMemberHobby.memberHobby;

import com.atwoz.member.domain.info.InfoRepository;
import com.atwoz.member.domain.info.option.Option;
import com.atwoz.member.domain.info.option.QOption;
import com.atwoz.member.domain.info.profile.Profile;
import com.atwoz.member.domain.info.profile.QProfile;
import com.atwoz.member.domain.info.style.QStyle;
import com.atwoz.member.exception.exceptions.info.option.OptionNotFoundException;
import com.atwoz.member.exception.exceptions.info.profile.ProfileNotFoundException;
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
public class InfoRepositoryImpl implements InfoRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public InfoSearchResponse findByMemberId(final Long memberId) {
        Profile profile = selectProfileByMemberId(memberId);
        Option option = selectOptionByMemberId(memberId);
        ProfileSearchResponse profileSearchResponse = ProfileSearchResponse.from(profile);
        OptionSearchResponse optionSearchResponse = OptionSearchResponse.from(option);

        List<HobbySearchResponse> hobbyCodes = selectHobbyResponseByMemberId(memberId);
        List<StyleSearchResponse> styleCodes = selectStyleResponseByMemberId(memberId);

        return new InfoSearchResponse(profileSearchResponse, optionSearchResponse, hobbyCodes, styleCodes);
    }

    private Profile selectProfileByMemberId(final Long memberId) {
        return Optional.ofNullable(queryFactory.select(QProfile.profile)
                .from(QProfile.profile)
                .where(QProfile.profile.memberId.eq(memberId))
                .fetchOne())
                .orElseThrow(ProfileNotFoundException::new);
    }

    private Option selectOptionByMemberId(final Long memberId) {
        return Optional.ofNullable(queryFactory.select(QOption.option)
                .from(QOption.option)
                .where(QOption.option.memberId.eq(memberId))
                .fetchOne())
                .orElseThrow(OptionNotFoundException::new);
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
        return queryFactory.select(QStyle.style)
                .from(QStyle.style)
                .where(QStyle.style.memberId.eq(memberId))
                .fetch()
                .stream()
                .map(StyleSearchResponse::from)
                .toList();
    }
}
