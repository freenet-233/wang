package com.wang.common.config;

import com.winning.mup.core.utils.ConfigConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读取配置信息
 */
@Component
@Slf4j
public class ConfigService {

    private static volatile Map<String, String> propertiesMap = new HashMap<>();
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    @Value("${project.maxLoginRetry:5}")
    private String maxLoginRetry;
    @Value("${project.loginRetryWaitingMinutes:10}")
    private String loginRetryWaitingMinutes;
    @Value("${project.maxFileUploadSize:20}")
    private String maxFileUploadSize;

    @PostConstruct
    public void loadConfig() {
        propertiesMap.put(ConfigConstant.LOGIN_MAX_RETRY_NUM, maxLoginRetry);
        propertiesMap.put(ConfigConstant.LOGIN_RETRY_WAITING_MINUTES, loginRetryWaitingMinutes);
        propertiesMap.put(ConfigConstant.MAX_FILE_UPLOAD_SIZE, maxFileUploadSize);
        propertiesMap.put(ConfigConstant.CAPTCHA_TIMEOUT, "120");
        // 请求冷却时间 600秒
        propertiesMap.put(ConfigConstant.REQUEST_WAIT_TIME_MAC, "600");
    }

    // @Scheduled(cron = "0 0/5 * * * ?")
    public void reload() {
        Set<String> keysSet = getConfigKeys();
        String keySql = "'" + StringUtils.join(keysSet, "','") + "'";
    }

    public String get(String key, String defaultValue) {
        try {
            lock.readLock().lock();
            String value = propertiesMap.get(key);
            if (StringUtils.isEmpty(value)) {
                return defaultValue;
            } else {
                return value.trim();
            }
        } finally {
            lock.readLock().unlock();
        }
    }

    private Set<String> getConfigKeys() {
        Set<String> keysSet = new HashSet<>();
        Field[] declaredFields = ConfigConstant.class.getDeclaredFields();
        try {
            for (Field declaredField : declaredFields) {
                keysSet.add(String.valueOf(declaredField.get(null)));
            }
        } catch (IllegalAccessException e) {
            log.error(e.getMessage(), e);
        }
        return keysSet;
    }

}
