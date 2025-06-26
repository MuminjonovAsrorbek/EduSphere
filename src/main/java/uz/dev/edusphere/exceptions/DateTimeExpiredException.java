package uz.dev.edusphere.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Created by: asrorbek
 * DateTime: 6/26/25 15:01
 **/

@Getter
public class DateTimeExpiredException extends RuntimeException {

    private final HttpStatus status;

    public DateTimeExpiredException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
