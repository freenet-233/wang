package com.wang.common.exception;

/**
 * @author wang
 */
public class UncheckedBusinessException extends RuntimeException {

    private static final long serialVersionUID = -8460356990632230194L;

    private final ErrorCode code;

    public UncheckedBusinessException(ErrorCode code) {
        super();
        this.code = code;
    }

    public UncheckedBusinessException(ErrorCode code, String message) {
        super(message);
        this.code = code;
    }

    public UncheckedBusinessException(String message, Throwable cause, ErrorCode code) {
        super(message, cause);
        this.code = code;
    }

    public UncheckedBusinessException(Throwable cause, ErrorCode code) {
        super(cause);
        this.code = code;
    }

    public ErrorCode getCode() {
        return code;
    }
}
