package com.ecommerce.project.exceptions;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


// 이 클래스를 전체(또는 특정 범위)의 @RestController 들에 대한 전역 예외 처리기로 사용하겠다는 의미이다.
// 여기 정의된 @ExceptionHandler 메서드들이 모든 컨트롤러에서 발생하는 예외를 가로채서 처리한다.
@RestControllerAdvice
public class MyGlobalExceptionHandler {

  // 이 메서드는 MethodArgumentNotValidException 타입의 예외가 발생했을 때 호출되도록 지정한다.
  // 주로 @Valid 검증 실패 시 스프링이 이 예외를 던지며, 이 메서드가 그 예외를 처리한다.
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> myMethodArgumentNotValidException(
      MethodArgumentNotValidException e) {
    // 전역 예외 처리 메서드 정의.
    // 파라미터 e : 발생한 MethodArgumentNotValidException 객체.
    // 반환 타입 Map<String, String> : "필드명" -> "에러 메시지" 형태의 맵을 응답으로 반환한다.

    Map<String, String> response = new HashMap<>();
    // 에러 정보를 담을 빈 Hash Map 을 생성한다.
    // key: 필드 이름, value: 해당 필드의 에러 메시지.

    e.getBindingResult().getAllErrors().forEach(err -> {
      // 에러 객체 err 를 FieldError 로 캐스팅하여, 어떤 필드에서 에러가 났는지 필드 이름을 가져온다.
      // 해당 에러에 대한 기본 메시지(예: @NotBlank 에서 설정한 message)를 가져온다.
      String fieldName = ((FieldError) (err)).getField();
      String message = err.getDefaultMessage();

      response.put(fieldName, message);
    });

    // @RestControllerAdvice 덕분에 이 Map 은 JSON 형태로 클라이언트에게 응답된다.
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<String> myResourceNotFoundException(ResourceNotFoundException e) {
    String message = e.getMessage();
    return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(APIException.class)
  public ResponseEntity<String> myAPIException(APIException e) {
    String message = e.getMessage();
    return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
  }
}
