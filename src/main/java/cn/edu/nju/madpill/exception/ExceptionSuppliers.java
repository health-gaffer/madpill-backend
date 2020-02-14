package cn.edu.nju.madpill.exception;

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
    public static final Supplier<BaseException> WAREHOUSE_NOT_FOUND = () -> new BaseException("找不到相应的仓库药品", 404);
}
