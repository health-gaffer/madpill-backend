package cn.edu.nju.madpill.exception;

import cn.edu.nju.madpill.dto.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <p>
 * <p>
 *
 * @author Shenmiu
 * @date 2020/2/7
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BaseException.class)
    public Result handle(BaseException e) {
        return Result.builder()
                .msg(e.msg)
                .code(e.code)
                .build();
    }
}
