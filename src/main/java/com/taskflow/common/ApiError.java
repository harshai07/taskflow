package com.taskflow.common;

import java.time.Instant;
import java.util.Map;

/**
 * A consistent JSON shape for every error the API returns.
 * `fieldErrors` is only populated for validation (400) responses.
 */
public record ApiError(
        Instant timestamp,
        int status,
        String error,
        String message,
        Map<String, String> fieldErrors
) {
    public static ApiError of(int status, String error, String message) {
        return new ApiError(Instant.now(), status, error, message, null);
    }

    public static ApiError validation(int status, String error, String message, Map<String, String> fieldErrors) {
        return new ApiError(Instant.now(), status, error, message, fieldErrors);
    }
}
