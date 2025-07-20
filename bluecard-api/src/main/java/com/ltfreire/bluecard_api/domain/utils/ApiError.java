package com.ltfreire.bluecard_api.domain.utils;


public record ApiError(
        String timestamp,
        int status,
        String error,
        String message,
        String path
) {
}

