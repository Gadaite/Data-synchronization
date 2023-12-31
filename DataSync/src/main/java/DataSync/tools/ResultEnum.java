package DataSync.tools;

/**
 * 异常信息枚举类
 */
public enum ResultEnum {
    /** 成功 */
    REQUEST_SUCCESS(200,"success"),

    /** 失败 */
    REQUEST_FAILED(-1,"fail"),

    /** 未知错误 */
    UNKNOWN_ERROR(500,"unknown error");

    /** 状态 */
    private Integer code;
    /** 含义 */
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
