package com.hudzaifah.Rest_steganografi.globalException;
import com.hudzaifah.Rest_steganografi.model.dto.commond.ErrorResponse;
import com.hudzaifah.Rest_steganografi.model.error.ResouceNotFound;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(ResouceNotFound.class)
    public ResponseEntity<ErrorResponse<ResouceNotFound>> handleResourceNotFound(ResouceNotFound ex) {
        ErrorResponse<ResouceNotFound> errorResponse = new ErrorResponse<ResouceNotFound>(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage()
        );
        return new ResponseEntity<ErrorResponse<ResouceNotFound>>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse<RuntimeException>> handleResourceNotFound(RuntimeException ex) {
        ErrorResponse<RuntimeException> errorResponse = new ErrorResponse<RuntimeException>(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage()
        );
        return new ResponseEntity<ErrorResponse<RuntimeException>>(errorResponse, HttpStatus.CONFLICT);
    }



    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse<IllegalArgumentException>> handleIllegalArgument(IllegalArgumentException ex) {
        ErrorResponse<IllegalArgumentException> errorResponse = new ErrorResponse<IllegalArgumentException>(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage()
        );
        return new ResponseEntity<ErrorResponse<IllegalArgumentException>>(errorResponse, HttpStatus.NOT_FOUND);
    }
}


