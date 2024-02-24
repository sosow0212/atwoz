package com.atwoz.member.ui.info;

import com.atwoz.member.application.info.style.StyleService;
import com.atwoz.member.ui.auth.support.auth.AuthMember;
import com.atwoz.member.ui.info.dto.StyleSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/info/style")
@RestController
public class StyleController {

    private final StyleService styleService;

    @GetMapping
    public ResponseEntity<List<StyleSearchResponse>> findMemberStyles(@AuthMember final Long memberId) {
        List<StyleSearchResponse> styles = styleService.findMemberStyles(memberId)
                .stream()
                .map(StyleSearchResponse::from)
                .toList();

        return ResponseEntity.ok()
                .body(styles);
    }
}
