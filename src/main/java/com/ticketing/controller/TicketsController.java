package com.ticketing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ticketing") // Added a base path for the controller
public class TicketsController {

    @GetMapping("/userTicketsList/{userId}")
    public String getUserTickets(@PathVariable Long userId, Model model) {
        model.addAttribute("userId", userId);
        return "ticketing/userTicketsList"; //  Return the name of your Thymeleaf template.
    }
      
    @GetMapping("/users/create/{userId}")
    public String createForm(@PathVariable Long userId, Model model) {
        model.addAttribute("formHeading", "Create New Ticket");
        model.addAttribute("userFormTitle", "Create New Ticket");
        model.addAttribute("userId", userId);
        return "ticketing/userTicket"; // Returns the same Thymeleaf template for create
    }
    
    @GetMapping("/users/update/{userId}/{ticketId}")
    public String updAateForm(@PathVariable Long userId, @PathVariable Long ticketId, Model model) {
        model.addAttribute("formHeading", "Update Ticket");
        model.addAttribute("userFormTitle", "UpdateTicket");
        model.addAttribute("userId", userId);
        model.addAttribute("ticketId", ticketId);
        return "ticketing/userTicket"; // Returns the same Thymeleaf template for create
    }
}

