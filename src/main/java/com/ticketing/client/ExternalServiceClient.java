package com.ticketing.client;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ticketing.model.DepartmentResponse;
import com.ticketing.model.ProjectResponse;
import com.ticketing.model.RoleResponse;
import com.ticketing.model.UserResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExternalServiceClient {

 private final RestTemplate restTemplate;
 
 public ExternalServiceClient(RestTemplate restTemplate) {
     this.restTemplate = restTemplate;
 }
 
 public List<DepartmentResponse> fetchDepartments(HttpClient client, String url) {    	 
     List<DepartmentResponse> departments = new ArrayList<>();
     
     HttpHeaders headers = new HttpHeaders();
     HttpEntity<Void> requestEntity = new HttpEntity<>(headers); // No body for GET

      try {
	         ResponseEntity<List<DepartmentResponse>> responseEntity = restTemplate.exchange(
	                 url,
	                 HttpMethod.GET,
	                 requestEntity,
	                 new ParameterizedTypeReference<List<DepartmentResponse>>() {}
	         );
	         
	         if (responseEntity.getStatusCode().is2xxSuccessful()) {
	             departments = responseEntity.getBody();
	             // Process the list of departments
	             System.out.println("Departments: " + departments);
	         } else {
	             System.err.println("GET Request failed with status code: " + responseEntity.getStatusCode());
	         }
      } catch (HttpClientErrorException e) {
     	    System.err.println("Client error during GET request. Status Code: " + e.getStatusCode());
     	    System.err.println("Response Body: " + e.getResponseBodyAsString());
     	    // Handle specific client errors (4xx)
     	    if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
     	        System.err.println("Resource not found at: " + url);
     	    } else if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
     	        System.err.println("Unauthorized access to: " + url);
     	    }
     	    // Add more specific error handling based on status codes
     	} catch (RestClientException e) {
     	    System.err.println("Error during GET request: " + e.getMessage());
     	    // Handle other RestTemplate related exceptions (e.g., connection issues)
     	} catch (Exception e) {
     	    System.err.println("An unexpected error occurred during the GET request: " + e.getMessage());
     	    // Handle any other unexpected exceptions
     	}
         

     return departments;
 }
 
 public List<ProjectResponse> fetchProjects(HttpClient client, String url) {
     List<ProjectResponse> projects = new ArrayList<>();

     HttpHeaders headers = new HttpHeaders();
     HttpEntity<Void> requestEntity = new HttpEntity<>(headers); // No body for GET

     try {
         ResponseEntity<List<ProjectResponse>> responseEntity = restTemplate.exchange(
                 url,
                 HttpMethod.GET,
                 requestEntity,
                 new ParameterizedTypeReference<List<ProjectResponse>>() {}
         );

         if (responseEntity.getStatusCode().is2xxSuccessful()) {
             projects = responseEntity.getBody();
             // Process the list of projects
             System.out.println("Projects: " + projects);
         } else {
             System.err.println("GET Request failed for projects with status code: " + responseEntity.getStatusCode());
         }
     } catch (HttpClientErrorException e) {
         System.err.println("Client error during GET request for projects. Status Code: " + e.getStatusCode());
         System.err.println("Response Body: " + e.getResponseBodyAsString());
         // Handle specific client errors (4xx)
         if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
             System.err.println("Project resource not found at: " + url);
         } else if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
             System.err.println("Unauthorized access to project resource: " + url);
         }
         // Add more specific error handling based on status codes
     } catch (RestClientException e) {
         System.err.println("Error during GET request for projects: " + e.getMessage());
         // Handle other RestTemplate related exceptions (e.g., connection issues)
     } catch (Exception e) {
         System.err.println("An unexpected error occurred during the GET request for projects: " + e.getMessage());
         // Handle any other unexpected exceptions
     }

     return projects;
 }
 
 
 public List<UserResponse> fethcUsersByDepartmentAndProject(HttpClient client, String url, Long departmentId, Long projectId) {    	
     List<UserResponse> users = new ArrayList<>();
    
     HttpHeaders headers = new HttpHeaders();
     HttpEntity<Void> requestEntity = new HttpEntity<>(headers); // No body for GET


      try {
    	  ResponseEntity<List<UserResponse>> responseEntity = restTemplate.exchange(
    			    url + "?departmentId={departmentId}&projectId={projectId}", // Append query parameters to the URL
    			    HttpMethod.GET,
    			    requestEntity,
    			    new ParameterizedTypeReference<List<UserResponse>>() {},
    			    departmentId, // Pass the departmentId as a parameter
    			    projectId    // Pass the projectId as a parameter
    			);

	         
	         if (responseEntity.getStatusCode().is2xxSuccessful()) {
	        	 users = responseEntity.getBody();
	             // Process the list of users
	             System.out.println("Users: " + users);
	         } else {
	             System.err.println("GET Request failed with status code: " + responseEntity.getStatusCode());
	         }
      } catch (HttpClientErrorException e) {
     	    System.err.println("Client error during GET request. Status Code: " + e.getStatusCode());
     	    System.err.println("Response Body: " + e.getResponseBodyAsString());
     	    // Handle specific client errors (4xx)
     	    if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
     	        System.err.println("Resource not found at: " + url);
     	    } else if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
     	        System.err.println("Unauthorized access to: " + url);
     	    }
     	    // Add more specific error handling based on status codes
     	} catch (RestClientException e) {
     	    System.err.println("Error during GET request: " + e.getMessage());
     	    // Handle other RestTemplate related exceptions (e.g., connection issues)
     	} catch (Exception e) {
     	    System.err.println("An unexpected error occurred during the GET request: " + e.getMessage());
     	    // Handle any other unexpected exceptions
     	}
         

     return users;
 }
 
 
 public UserResponse fethcUserByUserName(HttpClient client, String url, String userName) {    	
     UserResponse user = new UserResponse();
    
     HttpHeaders headers = new HttpHeaders();
     HttpEntity<Void> requestEntity = new HttpEntity<>(headers); // No body for GET
     
     System.out.println("url: "  +url + userName);

      try {
    	  ResponseEntity<UserResponse> responseEntity = restTemplate.exchange(
    			    url + "{userName}", // Append query parameters to the URL
    			    HttpMethod.GET,
    			    requestEntity,
    			    new ParameterizedTypeReference<UserResponse>() {},
    			    userName
    			);

	         
	         if (responseEntity.getStatusCode().is2xxSuccessful()) {
	        	 user = responseEntity.getBody();
	             // Process the list of users
	             System.out.println("Users: " + user);
	         } else {
	             System.err.println("GET Request failed with status code: " + responseEntity.getStatusCode());
	         }
      } catch (HttpClientErrorException e) {
     	    System.err.println("Client error during GET request. Status Code: " + e.getStatusCode());
     	    System.err.println("Response Body: " + e.getResponseBodyAsString());
     	    // Handle specific client errors (4xx)
     	    if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
     	        System.err.println("Resource not found at: " + url);
     	    } else if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
     	        System.err.println("Unauthorized access to: " + url);
     	    }
     	    // Add more specific error handling based on status codes
     	} catch (RestClientException e) {
     	    System.err.println("Error during GET request: " + e.getMessage());
     	    // Handle other RestTemplate related exceptions (e.g., connection issues)
     	} catch (Exception e) {
     	    System.err.println("An unexpected error occurred during the GET request: " + e.getMessage());
     	    // Handle any other unexpected exceptions
     	}
         

     return user;
 }
 
 
 public List<RoleResponse> fethcRolesByUser(HttpClient client, String url, Long userId) {    	
	 List<RoleResponse> roles = new ArrayList<>();
    
     HttpHeaders headers = new HttpHeaders();
     HttpEntity<Void> requestEntity = new HttpEntity<>(headers); // No body for GET

      try {
    	  ResponseEntity<List<RoleResponse>> responseEntity = restTemplate.exchange(
    			    url + "{userId}", // Append query parameters to the URL
    			    HttpMethod.GET,
    			    requestEntity,
    			    new ParameterizedTypeReference<List<RoleResponse>>() {},
    			    userId
    			);

	         
	         if (responseEntity.getStatusCode().is2xxSuccessful()) {
	        	 roles = responseEntity.getBody();
	             // Process the list of users
	             System.out.println("Users: " + roles);
	         } else {
	             System.err.println("GET Request failed with status code: " + responseEntity.getStatusCode());
	         }
      } catch (HttpClientErrorException e) {
     	    System.err.println("Client error during GET request. Status Code: " + e.getStatusCode());
     	    System.err.println("Response Body: " + e.getResponseBodyAsString());
     	    // Handle specific client errors (4xx)
     	    if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
     	        System.err.println("Resource not found at: " + url);
     	    } else if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
     	        System.err.println("Unauthorized access to: " + url);
     	    }
     	    // Add more specific error handling based on status codes
     	} catch (RestClientException e) {
     	    System.err.println("Error during GET request: " + e.getMessage());
     	    // Handle other RestTemplate related exceptions (e.g., connection issues)
     	} catch (Exception e) {
     	    System.err.println("An unexpected error occurred during the GET request: " + e.getMessage());
     	    // Handle any other unexpected exceptions
     	}
         

     return roles;
 }
 
 public UserResponse fetchUserById(String url, Long userId) {
	 System.out.println("Fetching user by ID from URL: " + url + userId);
     UserResponse user = null; // Initialize to null
     HttpHeaders headers = new HttpHeaders();
     HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

     System.out.println("Fetching user by ID from URL: " + url + userId);

     try {
         ResponseEntity<UserResponse> responseEntity = restTemplate.exchange(
             url + "{userId}", // The endpoint URL with a path variable
             HttpMethod.GET,
             requestEntity,
             new ParameterizedTypeReference<UserResponse>() {},
             userId // The value for the path variable
         );

         if (responseEntity.getStatusCode().is2xxSuccessful()) {
             user = responseEntity.getBody();
             System.out.println("Successfully fetched User by ID: " + user);
         } else {
             System.err.println("GET Request failed for user by ID with status code: " + responseEntity.getStatusCode());
         }
     } catch (HttpClientErrorException e) {
         System.err.println("Client error fetching user by ID. Status Code: " + e.getStatusCode());
         System.err.println("Response Body: " + e.getResponseBodyAsString());
         if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
             System.err.println("User resource not found at: " + url + userId);
             // You might choose to re-throw a specific exception here if needed
         }
         throw e; // Re-throw to propagate error to the calling method
     } catch (RestClientException e) {
         System.err.println("Error during GET request for user by ID: " + e.getMessage());
         throw e; // Re-throw
     } catch (Exception e) {
         System.err.println("An unexpected error occurred during GET request for user by ID: " + e.getMessage());
         throw e; // Re-throw
     }
     return user;
 }
 

}