package com.ticketing.controller;
import java.net.http.HttpClient;
import java.security.Principal;
import java.util.ArrayList;
//
//import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
//import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;

import com.ticketing.domain.Department;
import com.ticketing.domain.Project;
import com.ticketing.domain.Role;
import com.ticketing.domain.User;
import com.ticketing.dto.NewUserDTO;
import com.ticketing.model.DepartmentResponse;
import com.ticketing.model.ProjectResponse;
import com.ticketing.model.RoleResponse;
import com.ticketing.model.UserResponse;
import com.ticketing.service.DepartmentService;
import com.ticketing.service.ProjectService;
import com.ticketing.service.RoleService;
import com.ticketing.service.UserService;
import com.ticketing.client.ExternalServiceClient;
//
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/admin/users")
@SessionAttributes("user")
public class UserController {

    private final SecurityFilterChain apiFilterChain2;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;
    
    @Autowired
    private DepartmentService departmentService;
    
    @Autowired
    private ProjectService projectService;
    
    @Autowired
    private RestTemplate restTemplate;
    

    private final ExternalServiceClient externalServiceClient; // Inject the service
    
    private final String userAdminBaseUrl = "http://localhost:8282"; // Define base URL


    UserController(SecurityFilterChain apiFilterChain2, ExternalServiceClient externalServiceClient) {
        this.apiFilterChain2 = apiFilterChain2;
        this.externalServiceClient = externalServiceClient;
    }
   
    
    @GetMapping()
    public String usersList(Model model) {
     model.addAttribute("formHeading", "Users Management");
     model.addAttribute("userFormTitle", "Users Management");

     List<User> users = userService.findAll();
     model.addAttribute("users", users); // Add the users to the model

     return "admin/users";
    }
    
    @GetMapping("/list")
    public ResponseEntity<List<User>> listUsers() {
     List<User> users = userService.findAll();
     return ResponseEntity.ok(users);
    }
    
    @GetMapping("/dashboard")
    public String showDashboard(Model model, Principal principal) { 	
        if (principal != null) {
            String username = principal.getName();
            String userServiceUrl = userAdminBaseUrl + "/api/admin/users/userName/";
            
            HttpClient client = HttpClient.newHttpClient();
        	UserResponse user = externalServiceClient.fethcUserByUserName(client, userServiceUrl, username);
        	
        	if (user != null) {
	        	System.out.println("principal 1: " + principal);
	        	System.out.println("userId 1: " + user.getUserId());
	        	System.out.println("user: " + user);
	        	
	        	String roleServiceUrl = userAdminBaseUrl + "/api/admin/roles/";
	            
	        	List<RoleResponse> userRoles = externalServiceClient.fethcRolesByUser(client, roleServiceUrl, user.getUserId());
	        	
	        	
	        	System.out.println("userRoles: " + userRoles);
	            
	            model.addAttribute("principal", principal);
	        	model.addAttribute("userId", user.getUserId());
	        	model.addAttribute("userRoles", userRoles);
	        	
	        	return "ticketing/dashboard";
        	
        	} else {
                // Handle the case where the UserService returns an error
                System.err.println("Error fetching user from UserService");
                // Optionally add an error message to the model to display to the user
                model.addAttribute("errorMessage", "Error fetching user details.");
                return "error"; // Or redirect to an error page
            }
        	
        }
        return "login";
    }

   
    
    @GetMapping("/test")
    public String test(Model model) {
        return "test"; // Returns the same Thymeleaf template for create
    }
    
    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("formHeading", "Create New User");
        model.addAttribute("userFormTitle", "Create New User");
        return "admin/signup"; // Returns the same Thymeleaf template for create
    }
    
    @GetMapping("/edit/{userId}")
    public String editUserForm(@PathVariable Long userId, Model model) {
        User user = userService.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
        
        List<Role> userRoles = new ArrayList<>(user.getRoles()); // Convert Set to List for easier JSON handling
        
        model.addAttribute("formHeading", "Edit User");
        model.addAttribute("userFormTitle", "Edit User");
        model.addAttribute("userId", user.getUserId());
        model.addAttribute("userName", user.getUserName());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("password", user.getUserPassword());        
        model.addAttribute("selectedDepartmentId", user.getDepartment() != null ? user.getDepartment().getDepartmentId() : null);
        model.addAttribute("selectedProjectId", user.getProject() != null ? user.getProject().getProjectId() : null);      
        model.addAttribute("selectedManagerId", user.getManager() != null ? user.getManager().getUserId() : null);
        
        model.addAttribute("roles", userRoles);
        return "admin/signup"; // Returns the Thymeleaf template named "signup.html"
    }
    
    
    @PostMapping("/save")
    @ResponseBody
    public ResponseEntity<ApiResponse> saveUser(@RequestBody NewUserDTO userUpdateRequest) {
        try {
            User user;
            if (userUpdateRequest.getId() != null) {
                user = userService.findById(userUpdateRequest.getId()).orElseThrow(() -> new RuntimeException("User not found"));
                user.setUserName(userUpdateRequest.getUserName());
                user.setEmail(userUpdateRequest.getEmail());
            } else {
                user = new User();
                user.setUserName(userUpdateRequest.getUserName());
                user.setEmail(userUpdateRequest.getEmail());

                if (userUpdateRequest.getPassword() == null || userUpdateRequest.getPassword().isEmpty()) {
                    return ResponseEntity.badRequest().body(new ApiResponse(false, "Password is required for new users."));
                }
                user.setUserPassword(new BCryptPasswordEncoder().encode(userUpdateRequest.getPassword()));
            }

            // Handle Manager
            if (userUpdateRequest.getManager() != null) {
                userService.findById(userUpdateRequest.getManager())
                        .ifPresentOrElse(user::setManager, () -> {
                            throw new RuntimeException("Manager not found with ID: " + userUpdateRequest.getManager());
                        });
            } else {
                user.setManager(null); // Allow setting manager to null
            }

            // Handle Department
            if (userUpdateRequest.getDepartment() != null) {
                departmentService.findById(userUpdateRequest.getDepartment())
                        .ifPresentOrElse(user::setDepartment, () -> {
                            throw new RuntimeException("Department not found with ID: " + userUpdateRequest.getDepartment());
                        });
            } else {
                user.setDepartment(null); // Allow setting department to null
            }

            // Handle Project
            if (userUpdateRequest.getProject() != null) {
                projectService.findById(userUpdateRequest.getProject())
                        .ifPresentOrElse(user::setProject, () -> {
                            throw new RuntimeException("Project not found with ID: " + userUpdateRequest.getProject());
                        });
            } else {
                user.setProject(null); // Allow setting project to null
            }

            // Handle Roles
            Set<Role> roles = userUpdateRequest.getRoles().stream()
                    .map(roleId -> roleService.findById(roleId)
                            .orElseThrow(() -> new RuntimeException("Role not found with ID: " + roleId)))
                    .collect(Collectors.toSet());
            user.setRoles(roles);

            userService.save(user);
            return ResponseEntity.ok(new ApiResponse(true, "User saved successfully."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, "Error saving user: " + e.getMessage()));
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return "redirect:/admin/users";
    }
    
}