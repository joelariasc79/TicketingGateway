package com.ticketing.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails; // Import UserDetails

import com.ticketing.client.ExternalServiceClient;
import com.ticketing.model.DepartmentResponse;
import com.ticketing.model.UserResponse;

import java.net.http.HttpClient;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/ticketing")
@SessionAttributes("user")
public class DashboardControllerDelete {

    private final String ticketingAdminAPIEndPointurl = "http://localhost:8282/";
    private final String ticketingAdminAPIurl = ticketingAdminAPIEndPointurl + "api/admin/users/";

    private final ExternalServiceClient externalServiceClient; // Inject the service

    public DashboardControllerDelete(ExternalServiceClient externalServiceClient) {
        this.externalServiceClient = externalServiceClient;
    }

    // Get UserId from Admin Endpoint
//    @GetMapping("/dashboard")
//    public String dashboard(Authentication authentication, Model model) {
//    	final String ticketingAdminUserNameAPIurl = ticketingAdminAPIurl + "userName/";
//    	
//    	System.out.println("ticketingAdminUserNameAPIurl: " + ticketingAdminUserNameAPIurl);
//    	
//        if (authentication != null && authentication.isAuthenticated()) {
//            List<String> roles = authentication.getAuthorities().stream()
//                    .map(GrantedAuthority::getAuthority)
//                    .collect(Collectors.toList());
//            model.addAttribute("userRoles", roles);
//
//            Long userId = null;
//            if (authentication.getPrincipal() instanceof UserDetails) {
//                UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//                
//                HttpClient client = HttpClient.newHttpClient();
//                
//                // Fetch User
////                System.out.println("userDetails.getUsername(): " + userDetails.getUsername());
//                UserResponse user = externalServiceClient.fethcUserByUserName(client, ticketingAdminUserNameAPIurl, userDetails.getUsername());
//                userId = user.getUserId();
//                System.out.println("userId: " + userId);
//                model.addAttribute("userId", userId);
//                
//                //  Get the userId using the userService
////                userId = userService.getUserIdByUsername(userDetails.getUsername());
////                model.addAttribute("userId", userId);
//                model.addAttribute("principal", userDetails); //Also send userDetails
//            } else {
//                // Handle cases where the principal might be a different type
//                model.addAttribute("principal", authentication.getPrincipal());
//                //  You might want to log this or handle it in a way that makes sense for your application.
//                //  For example, you might set userId to a default value or show an error.
//                userId = 0L;  // Or some default value
//                model.addAttribute("userId", userId);
//            }
//            model.addAttribute("userId", userId); //redundant
//        } else {
//            return "login";
//        }
//        return "ticketing/dashboard";
//    }
}