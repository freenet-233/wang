package com.wang.common.filter;

import com.alibaba.fastjson2.filter.ValueFilter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DataMaskingFilter implements ValueFilter {

    public static void main(String[] args) {
        String value = "yy@qq.com";
        System.out.println(value.replaceAll("(\\w{1})\\w*(\\w{1})@(\\w+)", "$1****$2@$3"));
        value = "021-12345678";
        System.out.println(value.replaceAll("(\\d{3})\\d*(\\d{3})", "$1****$2"));
        value = "31019930213854X";
        System.out.println(value.replaceAll("(\\d{3})\\d*(\\w{2})", "$1*********$2"));
    }

    @Override
    public Object apply(Object object, String name, Object value) {
        if (!(value instanceof String) || ((String) value).length() == 0) {
            return value;
        }
        String nameLowerCase = name.toLowerCase();
        if (nameLowerCase.contains("phone") || "contactNumber".equalsIgnoreCase(nameLowerCase) || "dh".equalsIgnoreCase(nameLowerCase)
                || "idNumber".equalsIgnoreCase(nameLowerCase)) {
            return ((String) value).replaceAll("(\\d{3})\\d*(\\d{3})", "$1****$2");
        } else if (nameLowerCase.contains("idcard") || nameLowerCase.contains("certno")) {
            return ((String) value).replaceAll("(\\d{3})\\d*(\\w{2})", "$1******$2");
        } else if (nameLowerCase.contains("email")) {
            return ((String) value).replaceAll("(\\w{1})\\w*(\\w{1})@(\\w+)", "$1****$2@$3");
        } else if (nameLowerCase.contains("bankcardno")) {
            return ((String) value).replaceAll("(\\d{4})\\d*(\\d{4})", "$1****$2");
        }
        return value;
    }
}
