package com.atwoz.mission.exception.mission;

import com.atwoz.mission.exception.mission.exceptions.MissionNotClearException;
import com.atwoz.mission.exception.mission.exceptions.MissionNotFoundException;
import com.atwoz.mission.exception.mission.exceptions.MissionTypeInvalidException;
import com.atwoz.mission.exception.mission.exceptions.PublicOptionInvalidException;
import com.atwoz.mission.exception.mission.exceptions.RewardValueInvalidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MissionExceptionHandler {

    @ExceptionHandler(MissionNotClearException.class)
    public ResponseEntity<String> handleMissionNotClearException(final MissionNotClearException e) {
        return getBadRequestResponse(e);
    }

    @ExceptionHandler(MissionNotFoundException.class)
    public ResponseEntity<String> handleMissionNotFoundException(final MissionNotFoundException e) {
        return getNotFoundResponse(e);
    }

    @ExceptionHandler(MissionTypeInvalidException.class)
    public ResponseEntity<String> handleMissionTypeInvalidException(final MissionTypeInvalidException e) {
        return getBadRequestResponse(e);
    }

    @ExceptionHandler(PublicOptionInvalidException.class)
    public ResponseEntity<String> handlePublicOptionInvalidException(final PublicOptionInvalidException e) {
        return getBadRequestResponse(e);
    }

    @ExceptionHandler(RewardValueInvalidException.class)
    public ResponseEntity<String> handleRewardValueInvalidException(final RewardValueInvalidException e) {
        return getBadRequestResponse(e);
    }

    private ResponseEntity<String> getBadRequestResponse(final Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }

    private ResponseEntity<String> getNotFoundResponse(final Exception e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }
}
