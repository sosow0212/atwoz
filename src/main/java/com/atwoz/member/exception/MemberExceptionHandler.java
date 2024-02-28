package com.atwoz.member.exception;

import com.atwoz.member.exception.exceptions.auth.ExpiredTokenException;
import com.atwoz.member.exception.exceptions.auth.JsonDataInvalidException;
import com.atwoz.member.exception.exceptions.auth.LoginInvalidException;
import com.atwoz.member.exception.exceptions.auth.OAuthPlatformNotFountException;
import com.atwoz.member.exception.exceptions.auth.SignatureInvalidException;
import com.atwoz.member.exception.exceptions.auth.TokenFormInvalidException;
import com.atwoz.member.exception.exceptions.auth.TokenInvalidException;
import com.atwoz.member.exception.exceptions.auth.UnsupportedTokenException;
import com.atwoz.member.exception.exceptions.info.hobby.HobbyDuplicateException;
import com.atwoz.member.exception.exceptions.info.hobby.HobbyNotFoundException;
import com.atwoz.member.exception.exceptions.info.hobby.HobbySizeException;
import com.atwoz.member.exception.exceptions.info.option.DrinkInvalidException;
import com.atwoz.member.exception.exceptions.info.option.GraduateInvalidException;
import com.atwoz.member.exception.exceptions.info.option.MbtiInvalidException;
import com.atwoz.member.exception.exceptions.info.option.OptionNotFoundException;
import com.atwoz.member.exception.exceptions.info.option.ReligionInvalidException;
import com.atwoz.member.exception.exceptions.info.option.SmokeInvalidException;
import com.atwoz.member.exception.exceptions.info.profile.JobInvalidException;
import com.atwoz.member.exception.exceptions.info.profile.ProfileNotFoundException;
import com.atwoz.member.exception.exceptions.info.profile.body.AgeRangeException;
import com.atwoz.member.exception.exceptions.info.profile.body.GenderInvalidException;
import com.atwoz.member.exception.exceptions.info.profile.body.HeightRangeException;
import com.atwoz.member.exception.exceptions.info.profile.position.LatitudeRangeException;
import com.atwoz.member.exception.exceptions.info.profile.position.LongitudeRangeException;
import com.atwoz.member.exception.exceptions.info.style.StyleDuplicateException;
import com.atwoz.member.exception.exceptions.info.style.StyleNotFoundException;
import com.atwoz.member.exception.exceptions.info.style.StyleSizeException;
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

    // info - hobby
    @ExceptionHandler(HobbyNotFoundException.class)
    public ResponseEntity<String> handleHobbyNotFoundException(final HobbyNotFoundException e) {
        return getNotFoundResponse(e);
    }

    @ExceptionHandler(HobbySizeException.class)
    public ResponseEntity<String> handleHobbySizeException(final HobbySizeException e) {
        return getBadRequest(e);
    }

    @ExceptionHandler(HobbyDuplicateException.class)
    public ResponseEntity<String> handleHobbyDuplicationException(final HobbyDuplicateException e) {
        return getBadRequest(e);
    }

    // info - style
    @ExceptionHandler(StyleNotFoundException.class)
    public ResponseEntity<String> handleStyleNotFoundException(final StyleNotFoundException e) {
        return getNotFoundResponse(e);
    }

    @ExceptionHandler(StyleSizeException.class)
    public ResponseEntity<String> handleStyleSizeException(final StyleSizeException e) {
        return getBadRequest(e);
    }

    @ExceptionHandler(StyleDuplicateException.class)
    public ResponseEntity<String> handleStyleDuplicateException(final StyleDuplicateException e) {
        return getBadRequest(e);
    }

    // info - profile
    @ExceptionHandler(ProfileNotFoundException.class)
    public ResponseEntity<String> handleProfileNotFoundException(ProfileNotFoundException e) {
        return getNotFoundResponse(e);
    }

    @ExceptionHandler(AgeRangeException.class)
    public ResponseEntity<String> handleAgeRangeException(final AgeRangeException e) {
        return getBadRequest(e);
    }

    @ExceptionHandler(HeightRangeException.class)
    public ResponseEntity<String> handleHeightRangeException(final HeightRangeException e) {
        return getBadRequest(e);
    }

    @ExceptionHandler(GenderInvalidException.class)
    public ResponseEntity<String> handleGenderInvalidException(final GenderInvalidException e) {
        return getBadRequest(e);
    }

    @ExceptionHandler(LatitudeRangeException.class)
    public ResponseEntity<String> handleLatitudeRangeException(final LatitudeRangeException e) {
        return getBadRequest(e);
    }

    @ExceptionHandler(LongitudeRangeException.class)
    public ResponseEntity<String> handleLongitudeRangeException(final LongitudeRangeException e) {
        return getBadRequest(e);
    }

    @ExceptionHandler(JobInvalidException.class)
    public ResponseEntity<String> handleJobInvalidException(final JobInvalidException e) {
        return getBadRequest(e);
    }

    // info - option
    @ExceptionHandler(OptionNotFoundException.class)
    public ResponseEntity<String> handleOptionNotFoundException(final OptionNotFoundException e) {
        return getNotFoundResponse(e);
    }

    @ExceptionHandler(DrinkInvalidException.class)
    public ResponseEntity<String> handleDrinkNInvalidException(final DrinkInvalidException e) {
        return getBadRequest(e);
    }

    @ExceptionHandler(GraduateInvalidException.class)
    public ResponseEntity<String> handleGraduateInvalidException(final GraduateInvalidException e) {
        return getBadRequest(e);
    }

    @ExceptionHandler(MbtiInvalidException.class)
    public ResponseEntity<String> handleMbtiInvalidException(final MbtiInvalidException e) {
        return getBadRequest(e);
    }

    @ExceptionHandler(SmokeInvalidException.class)
    public ResponseEntity<String> handleSmokeInvalidException(final SmokeInvalidException e) {
        return getBadRequest(e);
    }

    @ExceptionHandler(ReligionInvalidException.class)
    public ResponseEntity<String> handleReligionInvalidException(final ReligionInvalidException e) {
        return getBadRequest(e);
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
