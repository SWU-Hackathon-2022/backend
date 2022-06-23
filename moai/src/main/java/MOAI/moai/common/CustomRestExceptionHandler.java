package MOAI.moai.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  컨트롤러 공통 예외 처리 로직
 */
@RestControllerAdvice
public class CustomRestExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException ex) {
        // 아예 잘못된 형식으로 request 를 요청할 경우 예외 발생
        Map<String, String> error = new HashMap<>();
        error.put("code", "ERR5001");
        error.put("message", "Required request body is missing");

        return ResponseEntity.badRequest().body(error);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Map<String, String>> handleMissingServletRequestParameterException(
            MissingServletRequestParameterException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("code", "ERR5002");
        error.put("message", ex.getParameterName() + " is missing");
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex) {

        List<String> params = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            params.add(error.getField());
        }
        String errorStr = String.join(",", params);

        Map<String, String> error = new HashMap<>();
        error.put("code", "ERR5003");
        error.put("message", errorStr + " is invalid");
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(BaseException.class)
    public final ResponseEntity<BaseResponse<BaseResponseStatus>> handleBaseException(BaseException exception) {
        return BaseResponse.toResponseEntity(exception);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex) {
        Map<String, String> error = new HashMap<>();
        error.put("code", "ERR5000");
        error.put("message", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

}
