package com.atwoz.member.ui.info;

import com.atwoz.member.application.info.hobby.HobbyService;
import com.atwoz.member.ui.auth.support.auth.AuthMember;
import com.atwoz.member.ui.info.dto.HobbySearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/info/hobby")
@RestController
public class HobbyController {

    private final HobbyService hobbyService;

    @GetMapping
    public ResponseEntity<List<HobbySearchResponse>> findMemberHobbies(@AuthMember final Long memberId) {
        List<HobbySearchResponse> hobbies = hobbyService.findMemberHobbies(memberId)
                .stream()
                .map(HobbySearchResponse::from)
                .toList();

        return ResponseEntity.ok()
                .body(hobbies);
    }
}
