package com.atwoz.member.ui.auth;

import com.atwoz.member.application.auth.AuthService;
import com.atwoz.member.application.auth.dto.LoginRequest;
import com.atwoz.member.ui.auth.dto.TokenResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login/{provider}")
    public ResponseEntity<TokenResponse> login(@PathVariable final String provider,
                                                @RequestBody @Valid final LoginRequest request) {
        String token = authService.login(provider, request);
        return ResponseEntity.ok(new TokenResponse(token));
    }
}
