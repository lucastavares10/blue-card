package com.ltfreire.bluecard_api.infra.exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ltfreire.bluecard_api.domain.utils.ApiError;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;

public class ExceptionResponseWriter {

    private static final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

    public static void writeError(HttpServletResponse response, int status, String error, String message) throws IOException {
        response.setStatus(status);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        ApiError apiError = new ApiError(
                LocalDateTime.now().toString(),
                status,
                error,
                message,
                null
        );

        response.getWriter().write(mapper.writeValueAsString(apiError));
    }
}
