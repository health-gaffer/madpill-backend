package cn.edu.nju.madpill.utils;

/**
 * @author charles
 * @date 2020/02/18
 */
public final class MadPillConstant {

    public static final String HEADER_MADPILL_TOKEN_KEY = "madpill-token";

    private MadPillConstant() {
    }

    public final class MadPillStatus {
        public static final String EXPIRED = "expired";
        public static final String NOT_EXPIRED = "notExpired";
        public static final String EXPIRING = "expiring";
    }
}
