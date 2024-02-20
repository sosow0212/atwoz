package com.atwoz.member.application.member;

import com.atwoz.member.application.event.HobbyWriteEvent;
import com.atwoz.member.application.event.StyleWriteEvent;
import com.atwoz.member.application.event.ValidatedLoginEvent;
import com.atwoz.member.application.info.dto.HobbyWriteRequest;
import com.atwoz.member.application.info.dto.StyleWriteRequest;
import com.atwoz.member.domain.info.hobby.Hobbies;
import com.atwoz.member.domain.info.style.Styles;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import java.util.List;

@RequiredArgsConstructor
@Component
public class MemberEventHandler {

    private final MemberService memberService;

    @EventListener
    public void registerIfNotMemberExist(final ValidatedLoginEvent event) {
        memberService.create(event.getEmail(), event.getNickname());
    }

    @EventListener
    public void updateMemberHobbies(final HobbyWriteEvent event) {
        Long memberId = event.getMemberId();
        List<String> hobbyNames = event.getHobbies()
                .stream()
                .map(HobbyWriteRequest::hobby)
                .toList();
        Hobbies hobbies = new Hobbies(memberId, hobbyNames);
        memberService.updateHobbies(memberId, hobbies);
    }

    @EventListener
    public void updateMemberStyles(final StyleWriteEvent event) {
        Long memberId = event.getMemberId();
        List<String> styleNames = event.getStyles()
                .stream()
                .map(StyleWriteRequest::style)
                .toList();
        Styles styles = new Styles(memberId, styleNames);
        memberService.updateStyles(memberId, styles);
    }
}
