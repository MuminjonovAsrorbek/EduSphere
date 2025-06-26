package uz.dev.edusphere.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.dev.edusphere.dto.response.ErrorDTO;
import uz.dev.edusphere.dto.response.FieldErrorDTO;
import uz.dev.edusphere.exceptions.DateTimeExpiredException;
import uz.dev.edusphere.exceptions.EntityAlreadyExist;
import uz.dev.edusphere.exceptions.EntityNotFoundException;
import uz.dev.edusphere.exceptions.PasswordIncorrectException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by: asrorbek
 * DateTime: 5/28/25 14:48
 **/

@RestControllerAdvice("uz.dev.edusphere")
public class GlobalExceptionHandler {

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ErrorDTO> handle(RuntimeException e) {

        ErrorDTO error = new ErrorDTO(
                500,
                "Internal Server Error: " + e.getMessage()
        );

        return ResponseEntity
                .status(500)
                .body(error);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> handle(MethodArgumentNotValidException e) {

        BindingResult bindingResult = e.getBindingResult();

        List<FieldErrorDTO> fieldErrors = new ArrayList<>();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            String fieldName = fieldError.getField();
            String message = fieldError.getDefaultMessage();
            fieldErrors.add(new FieldErrorDTO(fieldName, message));
        }

        ErrorDTO error = new ErrorDTO(
                400,
                "Field not valid",
                fieldErrors
        );

        return ResponseEntity
                .status(400)
                .body(error);
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<ErrorDTO> handle(EntityNotFoundException e) {

        ErrorDTO error = new ErrorDTO(
                e.getStatus().value(),
                e.getMessage()
        );

        return ResponseEntity
                .status(e.getStatus().value())
                .body(error);
    }


    @ExceptionHandler(value = PasswordIncorrectException.class)
    public ResponseEntity<ErrorDTO> handle(PasswordIncorrectException e) {

        ErrorDTO error = new ErrorDTO(
                e.getStatus().value(),
                e.getMessage()
        );

        return ResponseEntity
                .status(e.getStatus().value())
                .body(error);
    }

    @ExceptionHandler(value = DateTimeExpiredException.class)
    public ResponseEntity<ErrorDTO> handle(DateTimeExpiredException e) {

        ErrorDTO error = new ErrorDTO(
                e.getStatus().value(),
                e.getMessage()
        );

        return ResponseEntity
                .status(e.getStatus().value())
                .body(error);
    }

    @ExceptionHandler(value = EntityAlreadyExist.class)
    public ResponseEntity<ErrorDTO> handle(EntityAlreadyExist e) {

        ErrorDTO error = new ErrorDTO(
                e.getStatus().value(),
                e.getMessage()
        );

        return ResponseEntity
                .status(e.getStatus().value())
                .body(error);
    }

    @ExceptionHandler(value = AuthorizationDeniedException.class)
    public ResponseEntity<ErrorDTO> handle(AuthorizationDeniedException e) {

        ErrorDTO error = new ErrorDTO(
                403,
                "Access Denied: " + e.getMessage()
        );

        return ResponseEntity
                .status(403)
                .body(error);

    }
}