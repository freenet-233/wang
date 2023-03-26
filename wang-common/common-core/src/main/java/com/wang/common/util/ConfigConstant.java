package com.wang.common.util;

/**
 * 配置信息key
 */
public final class ConfigConstant {

    private ConfigConstant() {

    }

    /**
     * 默认登录失败重试次数
     */
    public static final String LOGIN_MAX_RETRY_NUM = "LOGIN_MAX_RETRY_NUM";

    /**
     * 多次登录失败后等待时间 （分钟）
     */
    public static final String LOGIN_RETRY_WAITING_MINUTES = "LOGIN_RETRY_WAITING_MINUTES";

    /**
     * 验证码有效时间 秒
     */
    public static final String CAPTCHA_TIMEOUT = "CAPTCHA_TIMEOUT";

    /**
     * mac地址请求冷却时间 秒
     */
    public static final String REQUEST_WAIT_TIME_MAC = "REQUEST_WAIT_TIME_MAC";

    /**
     * 文件最大上传大小 MB
     */
    public static final String MAX_FILE_UPLOAD_SIZE = "MAX_FILE_UPLOAD_SIZE";
}
