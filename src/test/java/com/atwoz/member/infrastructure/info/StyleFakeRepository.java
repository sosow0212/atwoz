package com.atwoz.member.infrastructure.info;

import com.atwoz.member.domain.info.style.Style;
import com.atwoz.member.domain.info.style.StyleRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StyleFakeRepository implements StyleRepository {

    private final Map<Long, Style> map = new HashMap<>();
    private long id = 1L;

    @Override
    public void save(final Style style) {
        Style newStyle = new Style(style.getMemberId(), style.getStyleName());
        map.put(id, newStyle);
        id++;
    }

    @Override
    public void saveAll(final List<Style> styles) {
        styles.stream()
                .map(style -> new Style(style.getMemberId(), style.getStyleName()))
                .forEach(style -> map.put(id++, style));
    }

    @Override
    public void deleteStylesByMemberId(final Long memberId) {
        map.values().removeIf(style -> memberId.equals(style.getMemberId()));
    }

    @Override
    public List<Style> findAllByMemberId(final Long memberId) {
        return map.values()
                .stream()
                .filter(style -> memberId.equals(style.getMemberId()))
                .toList();
    }
}
