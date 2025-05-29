package com.ticketing.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ticketing.model.DepartmentResponse;
import com.ticketing.model.ProjectResponse;
import com.ticketing.model.UserResponse;
import com.ticketing.client.ExternalServiceClient;

import java.net.http.HttpClient;
import java.util.List;


@Controller
@RequestMapping("/ticketingGateway/admin/users/resetPassword")
@SessionAttributes("user")
public class ResetPasswordController {
	
	private final String ticketingAdminAPIurl = "http://localhost:8282/"; 
	private final String ticketingAdminDepartmentsAPIurl = ticketingAdminAPIurl + "api/admin/departments/"; 
	private final String ticketingAdminProjectsAPIurl =  ticketingAdminAPIurl+ "api/admin/projects/"; 
	private final String ticketingAdminResetPasswordAPIurl =  ticketingAdminAPIurl+ "api/admin/users/resetPassword"; 
    
    private final ExternalServiceClient externalServiceClient; // Inject the service

    public ResetPasswordController(ExternalServiceClient externalServiceClient) {
        this.externalServiceClient = externalServiceClient;
    }
    
    @GetMapping("/find")
    public String showFindUsersForm(Model model) {
        final String departmentsUrl = ticketingAdminDepartmentsAPIurl + "all";
        final String projectsUrl = ticketingAdminProjectsAPIurl + "all"; // URL for projects

        HttpClient client = HttpClient.newHttpClient();

        // Fetch Departments
        List<DepartmentResponse> departments = externalServiceClient.fetchDepartments(client, departmentsUrl);
        model.addAttribute("departments", departments);

        // Fetch Projects
        List<ProjectResponse> projects = externalServiceClient.fetchProjects(client, projectsUrl);
        model.addAttribute("projects", projects);

        return "resetPassword/findUser";
    }
    
   
 // This method handles redirects with errors from the backend
    @GetMapping()
    public String resetPasswordFormWithError(@org.springframework.web.bind.annotation.RequestParam(value = "id", required = false) Long userId,
                                             @org.springframework.web.bind.annotation.RequestParam(value = "error", required = false) String error,
                                             Model model) {
        model.addAttribute("userId", userId);
        if (error != null) {
            model.addAttribute("error", error);
        }
        return "resetPassword/resetPassword";
    }
    

    @GetMapping("/filter")
    public String findUsersByDepartmentAndProject(
            @RequestParam(value = "departmentId", required = false) Long departmentId,
            @RequestParam(value = "projectId", required = false) Long projectId,
            Model model) 
    {
    	final String userFilterByDepatmentAndProjectUrl = ticketingAdminResetPasswordAPIurl + "/filter";
    	HttpClient client = HttpClient.newHttpClient();

        List<UserResponse> users = externalServiceClient.fethcUsersByDepartmentAndProject(client, userFilterByDepatmentAndProjectUrl,departmentId, projectId);
        model.addAttribute("users", users);

        return "resetPassword/findUser"; // Return to the same HTML page to display results
    }
    
    
    @GetMapping("/{userId}")
    public String showResetPasswordForm(@PathVariable Long userId, Model model,
                                        @org.springframework.web.bind.annotation.RequestParam(value = "error", required = false) String error,
                                        @org.springframework.web.bind.annotation.RequestParam(value = "success", required = false) String success) {
        model.addAttribute("userId", userId);
        if (error != null) {
            model.addAttribute("error", error);
        }
        if (success != null) {
            model.addAttribute("success", success);
        }
        return "resetPassword/resetPassword"; // Assuming your HTML file is named resetPassword.html
    }
}