package ssg.com.sellercommerce.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ssg.com.sellercommerce.exception.IllegalRequestException;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
    /**
     * 올바르지 않은 요청(request)으로 발생한 예외를 처리하는 메소드입니다.
     */
    @ExceptionHandler({IllegalRequestException.class})
    public ResponseEntity handleIllegalRequestException(Exception e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
