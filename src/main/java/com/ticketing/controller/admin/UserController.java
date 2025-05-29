package com.ticketing.controller.admin;
import java.net.http.HttpClient;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.ticketing.domain.Role;
import com.ticketing.domain.User;
import com.ticketing.model.RoleResponse;
import com.ticketing.model.UserResponse;
import com.ticketing.service.UserService;
import com.ticketing.client.ExternalServiceClient;

@Controller
@RequestMapping("/ticketingGateway/admin/users")
@SessionAttributes("user")
public class UserController {

    private final SecurityFilterChain apiFilterChain2;

    @Autowired
    private UserService userService;
    
    private final RestTemplate restTemplate;

    private final ExternalServiceClient externalServiceClient; // Inject the service
    
    private final String USER_ADMIN_BASE_URL = "http://localhost:8282/api/admin"; // Define base URL


    @Autowired
    UserController(SecurityFilterChain apiFilterChain2, ExternalServiceClient externalServiceClient, RestTemplate restTemplate) {
        this.apiFilterChain2 = apiFilterChain2;
        this.externalServiceClient = externalServiceClient;
        this.restTemplate = restTemplate;
    }
    
    @GetMapping("/test")
    public String test(Model model) {
        return "test"; // Returns the same Thymeleaf template for create
    }
    
    @GetMapping("/dashboard")
    public String showDashboard(Model model, Principal principal) { 
    	Long userId = null;
    	List<RoleResponse> userRoles = new ArrayList<>();
    	if (principal != null) {
    		String username = principal.getName();
            String userServiceUrl = USER_ADMIN_BASE_URL + "/users/userName/";
            HttpClient client = HttpClient.newHttpClient();
        	UserResponse user = externalServiceClient.fethcUserByUserName(client, userServiceUrl, username);
        	if (user != null) {
        		userId = user.getUserId();
        		String roleServiceUrl = USER_ADMIN_BASE_URL + "/roles/";
        		userRoles = externalServiceClient.fethcRolesByUser(client, roleServiceUrl, user.getUserId());
        	}
    		
    	}
    	
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

            System.out.println("User " + username + " has authorities: " + authorities); // <--- ADD THIS LINE
            model.addAttribute("principal", authentication); // For th:text="${principal.principal.getUsername()}"
            model.addAttribute("userId", userId);
            model.addAttribute("userRoles", userRoles);
            
            System.out.println("User " + username + " has authorities: " + authorities);
        }
       
        return "admin/dashboard";
    }
   
    
    @GetMapping()
    public String usersList(Model model) {
     model.addAttribute("formHeading", "Users Management");
     model.addAttribute("userFormTitle", "Users Management");

     List<User> users = userService.findAll();
     model.addAttribute("users", users); // Add the users to the model

     return "admin/users";
    }
    
    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("formHeading", "Create New User");
        model.addAttribute("userFormTitle", "Create New User");
        return "admin/signup"; // Returns the same Thymeleaf template for create
    }
    
    
 // Move to 8282, or add ajax.load() in singup.html
    @GetMapping("/{userId}/edit")
    public String editUserForm(@PathVariable Long userId, Model model) {
        UserResponse userResponse;
        String apiUrl = USER_ADMIN_BASE_URL + "/users/"; // Base URL for your user API
        System.out.println("apiUrl: " + apiUrl);

        try {
            // Call the ExternalServiceClient to fetch user data
        	userResponse = externalServiceClient.fetchUserById(apiUrl, userId);

            if (userResponse == null) {
                // This indicates the API returned null, or some non-error non-success status
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User data not returned from API for ID: " + userId);
            }

        } catch (HttpClientErrorException.NotFound e) {
            // Specific handling for 404 Not Found from the API call
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with ID: " + userId, e);
        } catch (HttpClientErrorException e) {
            // Catch other 4xx client errors (e.g., 401 Unauthorized, 403 Forbidden, 400 Bad Request)
            System.err.println("Client error fetching user data from API: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
            throw new ResponseStatusException(e.getStatusCode(), "API client error for user ID: " + userId + ". Message: " + e.getMessage(), e);
        } catch (RestClientException e) {
            // Catch connection errors or other RestTemplate specific issues
            System.err.println("REST client error during API call for user ID " + userId + ": " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Failed to connect to user API for ID: " + userId, e);
        } catch (Exception e) {
            // Catch any other unexpected exceptions
            System.err.println("An unexpected error occurred while fetching user data from API for ID " + userId + ": " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieve user data from API: " + e.getMessage(), e);
        }

        // Now populate the model attributes from the UserResponse fetched via the API
        model.addAttribute("formHeading", "Edit User");
        model.addAttribute("userFormTitle", "Edit User");
        model.addAttribute("userId", userResponse.getUserId());
        model.addAttribute("userName", userResponse.getUserName());
        model.addAttribute("email", userResponse.getEmail());
//        model.addAttribute("password", user.getUserPassword()); // Be cautious about exposing passwords via UI
        // userResponse.getDepartment()
        
        // Safely access nested DTOs and their IDs for department, project, and manager
        model.addAttribute("selectedDepartmentId", userResponse.getDepartment() != null ? userResponse.getDepartment() : null);
        model.addAttribute("selectedProjectId", userResponse.getProject() != null ? userResponse.getProject() : null);      
        model.addAttribute("selectedManagerId", userResponse.getManager() != null ? userResponse.getManager() : null);
        
        // Your UserResponse's getRoles() should return List<RoleResponse>
        // Ensure RoleResponse maps correctly to what your Thymeleaf expects for roles (e.g., has getRoleId() and getRoleName())
        model.addAttribute("roles", userResponse.getRoles());

        return "admin/signup"; // Returns the Thymeleaf template named "signup.html"
    }
    
    
 // move to 8282
    @GetMapping("/{userId}/delete")
    public String deleteUser(@PathVariable Long userId) {
        try {
            // Construct the full URL for the DELETE request
            String url = USER_ADMIN_BASE_URL + "/users/" + userId + "/delete";
            
            restTemplate.getForObject(url, String.class); // Assuming the response body is not critical, just success/failure

            System.out.println("Successfully initiated delete request for user ID: " + userId + " via HTTP to " + url);
        } catch (Exception e) {
            System.err.println("Error initiating delete request for user ID: " + userId + ": " + e.getMessage());
            // Handle error, e.g., add an error message to model or redirect to an error page
        }
        return "redirect:/ticketingGateway/admin/users"; // Redirect back to the user list
    }
}