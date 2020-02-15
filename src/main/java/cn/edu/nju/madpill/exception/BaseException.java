package cn.edu.nju.madpill.exception;

/**
 * <p>
 * <p>
 *
 * @author Shenmiu
 * @date 2020/2/6
 */
public class BaseException extends RuntimeException {
    protected final String msg;
    protected final int code;

    BaseException(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }
}
