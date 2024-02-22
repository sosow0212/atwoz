package com.atwoz.member.ui.info;

import com.atwoz.member.application.info.option.OptionService;
import com.atwoz.member.domain.info.option.Option;
import com.atwoz.member.ui.auth.support.auth.AuthMember;
import com.atwoz.member.ui.info.dto.option.OptionSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/info/option")
@RestController
public class OptionController {

    private final OptionService optionService;

    @GetMapping
    public ResponseEntity<OptionSearchResponse> findOption(@AuthMember Long memberId) {
        Option option = optionService.findByMemberId(memberId);
        OptionSearchResponse response = OptionSearchResponse.from(option);

        return ResponseEntity.ok()
                .body(response);
    }
}
