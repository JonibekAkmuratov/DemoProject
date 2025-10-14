package com.example.demoproject.config;

import com.example.demoproject.dto.AppErrorDTO;
import com.example.demoproject.dto.Data;
import com.example.demoproject.dto.main.auth.CurrentUserDetailsDTO;
import com.example.demoproject.events.TelegramAlarmEvent;
import com.example.demoproject.exceptions.*;
import com.example.demoproject.exceptions.key_exceptions.KeyCreateException;
import com.example.demoproject.utils.BaseUtils;
import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;



@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionHandlerConfig {
    private final ApplicationEventPublisher eventPublisher;
    private final BaseUtils utils;
    private final MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Data<AppErrorDTO>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException : {}", utils.getStackTrace(e));
        FieldError fieldError = e.getFieldErrors().stream().findFirst().orElse(null);
        return utils.isNotEmpty(fieldError)
                ? this.appErrorDto(fieldError.getDefaultMessage(), fieldError.getField())
                : this.appErrorDto(ErrorCode.UNKNOWN_ERROR, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Data<AppErrorDTO>> handleResourceNotFoundException(ResourceNotFoundException e) {
        log.error("ResourceNotFoundException : {}", utils.getStackTrace(e));
        return appErrorDto(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Data<AppErrorDTO>> handleBadRequestException(BadRequestException e) {
        log.error("BadRequestException : {}", utils.getStackTrace(e));
        return appErrorDto(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CamsClientException.class)
    public ResponseEntity<Data<AppErrorDTO>> handleCamsException(CamsClientException e) {
        log.error("CamsException : {}", utils.getStackTrace(e));
        return appErrorDto(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BasicException.class)
    public ResponseEntity<Data<AppErrorDTO>> handleBasicException(BasicException e) {
        return appErrorDto(e.getErrorMessage(), HttpStatus.valueOf(e.getCode()));
    }

    @ExceptionHandler({KeyNotFoundInCamsException.class})
    public ResponseEntity<Data<AppErrorDTO>> handleCMSKeyNotFound(KeyNotFoundInCamsException e) {
        log.error("KeyNotFoundInCamsException : {}", utils.getStackTrace(e));
        return appErrorDto(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PersistenceException.class)
    public ResponseEntity<Data<AppErrorDTO>> handlePersistenceException(PersistenceException e) {
        log.error("PersistenceException : {}", utils.getStackTrace(e));
        return appErrorDto(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({AuthorizationException.class})
    public ResponseEntity<Data<AppErrorDTO>> handleException(AuthorizationException e) {
        log.error("AuthorizationException : {}", utils.getStackTrace(e));
        return appErrorDto(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({MultipartException.class})
    public ResponseEntity<Data<AppErrorDTO>> handleMultipartException(MultipartException e) {
        log.error("MultipartException : {}", utils.getStackTrace(e));
        return this.appErrorDto(ErrorCode.FILE_SIZE_EXCEEDED_LIMIT, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({InternalServerErrorException.class})
    public ResponseEntity<Data<AppErrorDTO>> handleInternalServerErrorException(InternalServerErrorException e) {
        var stackTrace = utils.getStackTrace(e);
        log.error("InternalServerErrorException : {}", stackTrace);
        eventPublisher.publishEvent(new TelegramAlarmEvent(stackTrace));
        return this.appErrorDto(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({AccessDeniedException.class, org.springframework.security.access.AccessDeniedException.class})
    public ResponseEntity<Data<AppErrorDTO>> handleSpringAccessDeniedException(Exception e) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String serialNumber = ((CurrentUserDetailsDTO) principal).getSerialNumber();
            log.info("ACCESS_DENIED_EXCEPTION => SERIAL_NUMBER {}", serialNumber);
        }
        log.error("AccessDeniedException : {}", utils.getStackTrace(e));
        return appErrorDto(ErrorCode.ACCESS_DENIED, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({ValidationException.class})
    public ResponseEntity<Data<AppErrorDTO>> handleValidationException(ValidationException e) {
        log.error("ValidationException : {}", utils.getStackTrace(e));
        return appErrorDto(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({KeyCreateException.class})
    public ResponseEntity<Data<AppErrorDTO>> handleValidationException(KeyCreateException e) {
        log.error("KeyCreateException : {}", utils.getStackTrace(e));
        return appErrorDto(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MigrationSyncException.class)
    public ResponseEntity<Data<AppErrorDTO>> handleMigrationSyncException(MigrationSyncException e) {
        log.error("MigrationSyncException : {}", utils.getStackTrace(e));
        return appErrorDto(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({SilentException.class})
    public ResponseEntity<Data<AppErrorDTO>> handleSilentException(SilentException e) {
        return appErrorDto(e.getMessage(), HttpStatus.PARTIAL_CONTENT);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Data<AppErrorDTO>> handleException(Exception e) {
        log.error("Exception : {}", utils.getStackTrace(e));
        return appErrorDto(ErrorCode.UNKNOWN_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<Data<AppErrorDTO>> appErrorDto(String code, String argument) {
        String errorMessage = code;
        try {
            errorMessage = messageSource.getMessage(code, new Object[]{argument}, utils.getLocale());
        } catch (Exception ignore) {
        }
        AppErrorDTO appErrorDto = new AppErrorDTO(utils.getRequestURI(), errorMessage, code.toUpperCase());
        return new ResponseEntity<>(new Data<>(appErrorDto), HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Data<AppErrorDTO>> appErrorDto(String code, HttpStatus httpStatus) {
        String errorMessage = code;
        try {
            errorMessage = messageSource.getMessage(code, null, utils.getLocale());
        } catch (Exception ignore) {
        }
        AppErrorDTO appErrorDto = new AppErrorDTO(utils.getRequestURI(), errorMessage, code.toUpperCase());
        return new ResponseEntity<>(new Data<>(appErrorDto), httpStatus);
    }
}
