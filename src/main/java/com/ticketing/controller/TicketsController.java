package com.ticketing.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ticketing") // Added a base path for the controller
public class TicketsController {

    // ... (Other methods in your controller)

    @GetMapping("/userTickets/{userId}")
    public String getUserTickets(@PathVariable Long userId, Model model) {
        //  Retrieve the tickets for the specified userId.
        //  Replace this with your actual service call.
        //  Example:
        //  List<Ticket> userTickets = ticketService.getTicketsByUserId(userId);

        //  For now, I'll create a dummy list.
        //  You should replace this with your actual data retrieval.

        //  Add the user ID to the model, so you can use it in the Thymeleaf template if needed
        model.addAttribute("userId", userId);

        //  Return the name of your Thymeleaf template.
        return "ticketing/userTickets"; //  Create a template named userTickets.html
    }
    
//    @GetMapping("/dashboard")
//    public String dashboard(Authentication authentication, Model model) {
//        if (authentication != null && authentication.isAuthenticated()) {
//            // Get the user's authorities (roles) from the Authentication object.
//            List<String> roles = authentication.getAuthorities().stream()
//                    .map(GrantedAuthority::getAuthority)
//                    .collect(Collectors.toList());
//
//            // Add the roles to the model. This is what makes them available in your Thymeleaf template.
//            model.addAttribute("userRoles", roles);
//            
//            Long userId = null;
//            if (authentication.getPrincipal() instanceof UserDetails) {
//                UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//                // Assuming you have a method to get user ID from your UserDetails
//                //  Replace this with your actual method to get the user ID.
//                //  Example:  userId = userService.getUserIdByUsername(userDetails.getUsername());
//                userId = 123L; // Dummy Value
//                model.addAttribute("userId", userId); // Add userId to the model
//            }
//            else {
//                 //handle if the principal is not of type UserDetails
//                 model.addAttribute("userId", 0L); //Or some default
//            }
//
//        } else {
//            // If the user is not authenticated, you might want to redirect them to the login page
//            // or show a default dashboard with limited access.
//            return "login"; // Or some other appropriate view. Important to handle unauthenticated case!
//        }
//        return "dashboard"; // Return the name of your Thymeleaf template (dashboard.html)
//    }
}

