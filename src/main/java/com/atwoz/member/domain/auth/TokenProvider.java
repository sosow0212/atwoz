package com.atwoz.member.domain.auth;

public interface TokenProvider {

    String create(final String email);

    Long extract(final String token);
}
