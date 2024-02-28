package com.atwoz.member.infrastructure.info;

import com.atwoz.member.domain.info.style.MemberStyle;
import com.atwoz.member.domain.info.style.MemberStyleRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberStyleFakeRepository implements MemberStyleRepository {

    private final Map<Long, MemberStyle> map = new HashMap<>();
    private long id = 1L;

    @Override
    public void save(final MemberStyle style) {
        MemberStyle newStyle = MemberStyle.builder()
                .id(id)
                .memberId(style.getMemberId())
                .style(style.getStyle())
                .build();
        map.put(id, newStyle);
        id++;
    }

    @Override
    public void saveAll(final List<MemberStyle> styles) {
        styles.stream()
                .map(style -> new MemberStyle(style.getMemberId(), style.getStyle()))
                .forEach(style -> map.put(id++, style));
    }

    @Override
    public void deleteStylesByMemberId(final Long memberId) {
        map.values().removeIf(style -> memberId.equals(style.getMemberId()));
    }

    @Override
    public List<MemberStyle> findAllByMemberId(final Long memberId) {
        return map.values()
                .stream()
                .filter(style -> memberId.equals(style.getMemberId()))
                .toList();
    }
}
