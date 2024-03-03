package com.atwoz.member.infrastructure.info.style;

import com.atwoz.member.domain.info.style.MemberStyle;
import com.atwoz.member.domain.info.style.MemberStyleRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberStyleFakeRepository implements MemberStyleRepository {

    private final Map<Long, MemberStyle> map = new HashMap<>();
    private long id = 1L;

    @Override
    public void save(final MemberStyle memberStyle) {
        MemberStyle newMemberStyle = MemberStyle.builder()
                .id(id)
                .memberId(memberStyle.getMemberId())
                .style(memberStyle.getStyle())
                .build();
        map.put(id, newMemberStyle);
        id++;
    }

    @Override
    public void saveAll(final List<MemberStyle> memberStyles) {
        memberStyles.stream()
                .map(memberStyle -> new MemberStyle(memberStyle.getMemberId(), memberStyle.getStyle()))
                .forEach(memberStyle -> map.put(id++, memberStyle));
    }

    @Override
    public void deleteStylesByMemberId(final Long memberId) {
        map.values().removeIf(memberStyle -> memberId.equals(memberStyle.getMemberId()));
    }

    @Override
    public List<MemberStyle> findAllByMemberId(final Long memberId) {
        return map.values()
                .stream()
                .filter(memberStyle -> memberId.equals(memberStyle.getMemberId()))
                .toList();
    }
}
