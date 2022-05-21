package com.wang.common.domin;

import com.google.common.collect.ImmutableMap;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
public class ResponseMapBuilder {
    private final ImmutableMap.Builder<String, Object> variables = ImmutableMap.builder();


    public ResponseMapBuilder withData(final Object data) {

        int size = 1;
        if (data != null) {
            if (data instanceof List<?>) {
                size = ((List<?>) data).size();
            }
        }

        return withEntry("data", data).withEntry("total", size);
    }

    public ResponseMapBuilder putSuccess(final boolean success) {
        return withEntry("success", success);
    }

    public ResponseMapBuilder withEntry(final String key, final Object value) {
        if (value != null) {
            variables.put(key, value);
        }
        return this;
    }

    public Map<String, Object> newBuilder() {
        return variables.build();
    }

    static public ResponseMapBuilder createSuccessResponseMap() {
        return new ResponseMapBuilder().putSuccess(true);
    }

    static public ResponseMapBuilder createFailResponseMap() {
        return new ResponseMapBuilder().putSuccess(false);
    }

    static public ResponseMapBuilder createResponseMap(final Object data, final boolean success) {
        return new ResponseMapBuilder().withData(data).putSuccess(success);
    }

    static public ResponseMapBuilder createResponseMap(final String key, final Object value) {
        return new ResponseMapBuilder().withEntry(key, value);
    }
}
