package com.atwoz.member.application.info.style;

import com.atwoz.member.application.event.StyleWriteEvent;
import com.atwoz.member.application.info.dto.StyleWriteRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import java.util.List;

@RequiredArgsConstructor
public class StyleWriteEventHandler {

    private final StyleService styleService;

    @EventListener
    public void writeStyle(final StyleWriteEvent event) {
        List<String> styles = event.getStyles()
                .stream()
                .map(StyleWriteRequest::style)
                .toList();

        styleService.writeStyles(event.getMemberId(), styles);
    }
}
