package com.atwoz.member.infrastructure.info;

import com.atwoz.member.domain.info.option.Option;
import com.atwoz.member.domain.info.option.OptionRepository;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class OptionFakeRepository implements OptionRepository {

    private final Map<Long, Option> map = new HashMap<>();
    private long id = 1L;

    @Override
    public void save(final Option option) {
        Option newOption = new Option(
                option.getMemberId(),
                option.getSmoke(),
                option.getReligion(),
                option.getDrink(),
                option.getMbti(),
                option.getGraduate()
        );

        map.put(id, newOption);
        id++;
    }

    @Override
    public Optional<Option> findByMemberId(final Long memberId) {
        return Optional.ofNullable(map.get(memberId));
    }

    @Override
    public boolean isExistMemberOption(final Long memberId) {
        return findByMemberId(memberId).isPresent();
    }
}
