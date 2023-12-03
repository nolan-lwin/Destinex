package com.team08.csci205_final_project.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team08.csci205_final_project.exception.ExceptionResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

        @Override
        public void handle(HttpServletRequest request, HttpServletResponse response,
                           AccessDeniedException accessDeniedException) throws IOException, ServletException {

            ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), accessDeniedException.getMessage(), "You are not allowed to access this resource");

            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType("application/json");
            OutputStream responseStream = response.getOutputStream();
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(responseStream, exceptionResponse);
            responseStream.flush();

        }
}
