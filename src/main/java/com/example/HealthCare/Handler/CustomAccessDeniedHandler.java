package com.example.HealthCare.Handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.Principal;

@Component

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType("application/json");


            Principal principal = request.getUserPrincipal();
            String username = (principal != null) ? principal.getName() : "Unknown";
            String role = request.isUserInRole("ADMIN") ? "ADMIN"
                    : request.isUserInRole("DOCTOR") ? "DOCTOR"
                    : request.isUserInRole("NURSE") ? "NURSE"
                    : request.isUserInRole("PATIENT") ? "PATIENT"
                    : "UNKNOWN";

            String uri = request.getRequestURI();
            String requiredRole = "UNKNOWN";
            if (uri.startsWith("/admin")) requiredRole = "ADMIN";
            else if (uri.startsWith("/doctor")) requiredRole = "DOCTOR";
            else if (uri.startsWith("/nurse")) requiredRole = "NURSE";
            else if (uri.startsWith("/patient")) requiredRole = "PATIENT";


            String message = String.format("You are %s, not authorised for %s's page", role, requiredRole);

            response.getWriter().write("{\"error\": \"" + message + "\"}");
        }

    }
