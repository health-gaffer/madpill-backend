package cn.edu.nju.madpill.exception;

import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;

import java.util.function.Supplier;

/**
 * <p>
 * <p>
 *
 * @author Shenmiu
 * @date 2020/2/6
 */
@UtilityClass
public class ExceptionSuppliers {
    public static final Supplier<BaseException> DRUG_NOT_FOUND = () -> new BaseException("找不到相应的药品", 404);
    public static final Supplier<BaseException> WAREHOUSE_NOT_FOUND = () -> new BaseException("找不到相应的仓库药品", 404);
    public static final Supplier<BaseException> TAG_NOT_FOUND = () -> new BaseException("找不到相关标签", 404);
    public static final Supplier<BaseException> INVALID_TOKEN = () -> new BaseException("token 无效", HttpStatus.UNAUTHORIZED.value());
    public static final Supplier<BaseException> PERMISSION_DENIED = () -> new BaseException("没有相应权限", HttpStatus.FORBIDDEN.value());
}
