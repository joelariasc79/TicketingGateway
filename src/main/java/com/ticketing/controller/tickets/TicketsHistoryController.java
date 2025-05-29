package com.ticketing.controller.tickets;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/ticketingGateway")
public class TicketsHistoryController {
	@GetMapping("/viewHistory/{userId}/ticket/{ticketId}")
    public String viewTicketHistory(@PathVariable Long userId,
                                    @PathVariable Long ticketId,
                                    @RequestParam(name = "prev", required = false) String prev,
                                    Model model) {
        model.addAttribute("userId", userId);
        model.addAttribute("ticketId", ticketId);
        model.addAttribute("previousPageUrl", prev); // Pass 'prev' to the HTML

        return "ticketing/viewHistory"; // This maps to viewHistory.html template
    }

}