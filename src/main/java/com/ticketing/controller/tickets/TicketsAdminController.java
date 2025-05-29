package com.ticketing.controller.tickets;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ticketingGateway/admin")
public class TicketsAdminController {
	
	
	@GetMapping("/{userId}/ticketsList")
    public String getAdminTickets(@PathVariable Long userId, Model model) {
        model.addAttribute("userId", userId);
        return "ticketing/adminTicketsList"; //  Return the name of your Thymeleaf template.
    }
	
	@GetMapping("/{userId}/tickets/{ticketId}/edit")
    public String updateForm(@PathVariable Long userId, @PathVariable Long ticketId, Model model) {
        model.addAttribute("formHeading", "Approve Ticket");
        model.addAttribute("userFormTitle", "Approve");
        model.addAttribute("userId", userId);
        model.addAttribute("ticketId", ticketId);
        return "ticketing/adminTicket"; // Returns the same Thymeleaf template for create
    }
}
