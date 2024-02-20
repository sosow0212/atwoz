package com.atwoz.member.ui.info;

import com.atwoz.member.application.info.InfoService;
import com.atwoz.member.application.info.dto.InfoWriteRequest;
import com.atwoz.member.ui.auth.support.auth.AuthMember;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/info")
@RestController
public class InfoController {

    private final InfoService infoService;

    @PostMapping
    public ResponseEntity<Void> writeInfo(@AuthMember final Long memberId,
                                          @Valid @RequestBody final InfoWriteRequest request) {
        infoService.writeProfile(memberId, request);
        return ResponseEntity.ok()
                .build();
    }

}
