package com.atwoz.mission.exception.membermission;

import com.atwoz.mission.exception.membermission.exceptions.MemberMissionNotFoundException;
import com.atwoz.mission.exception.membermission.exceptions.MemberMissionsNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MemberMissionsExceptionHandler {

    @ExceptionHandler(MemberMissionsNotFoundException.class)
    public ResponseEntity<String> handleInfoNotFoundException(final MemberMissionsNotFoundException e) {
        return getNotFoundResponse(e);
    }

    @ExceptionHandler(MemberMissionNotFoundException.class)
    public ResponseEntity<String> handleMemberMissionNotFoundException(final MemberMissionNotFoundException e) {
        return getNotFoundResponse(e);
    }

    private ResponseEntity<String> getNotFoundResponse(final Exception e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }
}
