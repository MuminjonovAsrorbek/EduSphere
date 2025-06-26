package uz.dev.edusphere.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Created by: asrorbek
 * DateTime: 6/25/25 23:51
 **/

@Getter
public class EntityAlreadyExist extends RuntimeException {

    private final HttpStatus status;

    public EntityAlreadyExist(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
