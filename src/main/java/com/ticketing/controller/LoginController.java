package com.ticketing.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.SessionAttributes;

//
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@SessionAttributes("user")
public class LoginController {
    
	@GetMapping(value = "/login")
    public String login(@RequestParam(value = "logout", required = false) String logout,
                      @RequestParam(value = "error", required = false) String error,
                      HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Model model) {
        String message = "";
        if (error != null) {
            message = "Invalid Credentials. Please try again.";
            model.addAttribute("errorMessage", message); // Use a specific attribute for the error
        }
        if (logout != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null) {
                new SecurityContextLogoutHandler().logout(httpServletRequest, httpServletResponse, auth);
            }
            message = "Logout successful.";
            model.addAttribute("message", message); // Use a general message attribute for logout
        }
        // The username is already being passed, no need to change that.
        model.addAttribute("userName", httpServletRequest.getParameter("username"));
        return "login";
    }
    

}