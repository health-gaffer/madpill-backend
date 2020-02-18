package cn.edu.nju.madpill.exception;

import org.springframework.http.HttpStatus;

import java.util.function.Supplier;

/**
 * <p>
 * <p>
 *
 * @author Shenmiu
 * @date 2020/2/6
 */
public class ExceptionSuppliers {
    public static final Supplier<BaseException> DRUG_NOT_FOUND = () -> new BaseException("找不到相应的药品", 404);
    public static final Supplier<BaseException> INVALID_TOKEN = () -> new BaseException("token 无效", HttpStatus.UNAUTHORIZED.value());
}
