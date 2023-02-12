package shop.mtcoding.blogv1_2.customException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import shop.mtcoding.blogv1_2.customException.ex.CustomApiException;
import shop.mtcoding.blogv1_2.customException.ex.CustomException;
import shop.mtcoding.blogv1_2.dto.RespDto;
import shop.mtcoding.blogv1_2.util.CustomFileReturn;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public static ResponseEntity<?> CustomException(CustomException e) {
        String respBody = CustomFileReturn.Script(e.getMessage());
        return new ResponseEntity<>(respBody, e.getStatus());
    }

    @ExceptionHandler(CustomApiException.class)
    public static ResponseEntity<?> CustomException(CustomApiException e) {
        return new ResponseEntity<>(new RespDto<>(1, e.getMessage(), null), e.getStatus());
    }

}
