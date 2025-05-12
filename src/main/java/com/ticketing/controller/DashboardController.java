package com.ticketing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails; // Import UserDetails

import com.ticketing.client.ExternalServiceClient;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/ticketing")
@SessionAttributes("user")
public class DashboardController {

    private final String ticketingAdminAPIurl = "http://localhost:8282/";
    private final String ticketingAdminDepartmentsAPIurl = ticketingAdminAPIurl + "api/admin/departments/";

    private final ExternalServiceClient externalServiceClient; // Inject the service

    public DashboardController(ExternalServiceClient externalServiceClient) {
        this.externalServiceClient = externalServiceClient;
    }

    // Get UserId from Admin Endpoint
    @GetMapping("/dashboard")
    public String dashboard(Authentication authentication, Model model) {
        if (authentication != null && authentication.isAuthenticated()) {
            List<String> roles = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());
            model.addAttribute("userRoles", roles);

            // Explicitly add the principal to the model
            if (authentication.getPrincipal() instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                model.addAttribute("principal", userDetails);
            } else {
                // Handle cases where the principal might be a different type
                model.addAttribute("principal", authentication.getPrincipal());
            }

        } else {
            return "login";
        }
        return "ticketing/dashboard";
    }
}