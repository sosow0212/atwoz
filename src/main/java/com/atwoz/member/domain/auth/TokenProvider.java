package com.atwoz.member.domain.auth;

public interface TokenProvider {

    String createTokenWith(final String email);

    Long extract(final String token);
}
