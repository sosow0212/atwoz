package com.atwoz.member.exception;

import com.atwoz.member.exception.exceptions.auth.ExpiredTokenException;
import com.atwoz.member.exception.exceptions.auth.JsonDataInvalidException;
import com.atwoz.member.exception.exceptions.auth.LoginInvalidException;
import com.atwoz.member.exception.exceptions.auth.OAuthPlatformNotFountException;
import com.atwoz.member.exception.exceptions.auth.SignatureInvalidException;
import com.atwoz.member.exception.exceptions.auth.TokenFormInvalidException;
import com.atwoz.member.exception.exceptions.auth.TokenInvalidException;
import com.atwoz.member.exception.exceptions.auth.UnsupportedTokenException;
import com.atwoz.member.exception.exceptions.member.MemberAlreadyExistedException;
import com.atwoz.member.exception.exceptions.member.MemberNotFoundException;
import com.atwoz.member.exception.exceptions.member.PasswordNotMatchedException;
import com.atwoz.member.exception.exceptions.member.RoleNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MemberExceptionHandler {

    // member
    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<String> handleRoleNotFoundException(final RoleNotFoundException e) {
        return getNotFoundResponse(e);
    }

    @ExceptionHandler(MemberAlreadyExistedException.class)
    public ResponseEntity<String> handleMemberAlreadyExistedException(final MemberAlreadyExistedException e) {
        return getConflicted(e);
    }

    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<String> handleMemberNotFoundException(final MemberNotFoundException e) {
        return getNotFoundResponse(e);
    }

    @ExceptionHandler(PasswordNotMatchedException.class)
    public ResponseEntity<String> handlePasswordNotMatchedException(final PasswordNotMatchedException e) {
        return getConflicted(e);
    }

    // auth
    @ExceptionHandler(SignatureInvalidException.class)
    public ResponseEntity<String> handleSignatureInvalidException(final SignatureInvalidException e) {
        return getUnauthorized(e);
    }

    @ExceptionHandler(TokenFormInvalidException.class)
    public ResponseEntity<String> handleTokenFormInvalidException(final TokenFormInvalidException e) {
        return getUnauthorized(e);
    }

    @ExceptionHandler(ExpiredTokenException.class)
    public ResponseEntity<String> handleExpiredTokenException(final ExpiredTokenException e) {
        return getUnauthorized(e);
    }

    @ExceptionHandler(UnsupportedTokenException.class)
    public ResponseEntity<String> handleUnsupportedTokenException(final UnsupportedTokenException e) {
        return getUnauthorized(e);
    }

    @ExceptionHandler(TokenInvalidException.class)
    public ResponseEntity<String> handleTokenInvalidException(final TokenInvalidException e) {
        return getUnauthorized(e);
    }

    @ExceptionHandler(LoginInvalidException.class)
    public ResponseEntity<String> handleLoginInvalidException(final LoginInvalidException e) {
        return getUnauthorized(e);
    }

    @ExceptionHandler(JsonDataInvalidException.class)
    public ResponseEntity<String> handleJsonDataInvalidException(final JsonDataInvalidException e) {
        return getBadRequest(e);
    }

    @ExceptionHandler(OAuthPlatformNotFountException.class)
    public ResponseEntity<String> handleOAuthPlatformNotFountException(final OAuthPlatformNotFountException e) {
        return getNotFoundResponse(e);
    }

    private ResponseEntity<String> getNotFoundResponse(final Exception e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }

    private ResponseEntity<String> getUnauthorized(final Exception e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(e.getMessage());
    }

    private ResponseEntity<String> getConflicted(final Exception e) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(e.getMessage());
    }

    private ResponseEntity<String> getBadRequest(final Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }
}
