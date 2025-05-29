
package com.ticketing.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;






@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	UserDetailsService userDetailsService;
	

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
//	@Bean
//	public UserDetailsService userDetailsService() {
//		UserDetails user =
//			 User.withDefaultPasswordEncoder()
//				.username("user")
//				.password("password")
//				.roles("USER")
//				.build();
//
//		return new InMemoryUserDetailsManager(user);
//	}
	


//	@Bean 
//	public SecurityFilterChain apiFilterChain2(HttpSecurity http) throws Exception {
//		http
//			.apply(MyCustomDsl.customDsl())
//			.flag(true).and()
//			.authorizeRequests()
//				.requestMatchers("/", "/home").permitAll().and()
//			      .exceptionHandling().accessDeniedPage("/accessDeniedPage").and()
//			.authorizeRequests()
//				.requestMatchers("/userForm", "/submitUser", "/form","/submitForm").hasAnyAuthority("ADMIN").and()
//		.formLogin()
//			.loginPage("/login")
//			.defaultSuccessUrl("/userForm").permitAll().and()
//		.logout()
//		.logoutSuccessUrl("/")
//        .invalidateHttpSession(true)
//        .deleteCookies("JSESSIONID")
//        .permitAll();
//		
//		return http.build();
//	}
	
	

//	
//	
//	@Bean
//	public SecurityFilterChain apiFilterChain2(HttpSecurity http) throws Exception {	
//	    http
//	         .apply(MyCustomDsl.customDsl())
//	         .flag(true).and()
//		    .authorizeRequests()
//		    	.requestMatchers("/", "/**" , "/ticketing/dashboard", "/test","/home", "/create", "/admin/users", "/admin/users/list" ,"/admin/managers/all", "/admin/roles/all", "/admin/departments/all", "/admin/projects/all", "/error", "/save").permitAll().anyRequest().authenticated() 
//		    	.and()
//			    .exceptionHandling().accessDeniedPage("/accessDeniedPage")
//			    .and()
//	        .formLogin()
//	            .loginPage("/login")
//	            .defaultSuccessUrl("/admin/users/dashboard").permitAll()
//	            .and()
//	        .logout()
//	            .logoutSuccessUrl("/login")
//	            .invalidateHttpSession(true)
//	            .deleteCookies("JSESSIONID")
//	            .permitAll();
//
//	    return http.build();
//	}	
	
	
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception { // Changed method name from apiFilterChain2 for clarity
        http
             .apply(MyCustomDsl.customDsl()) // Keep your custom DSL if it's functional
             .flag(true).and() // Assuming this is needed by your custom DSL
            .authorizeRequests()
                // 1. PUBLIC ACCESS: Permit all users (even unauthenticated) to these paths
            .requestMatchers("/login", "/", "/error", "/css/**", "/js/**", "/images/**", "/ticketingGateway/admin/users/resetPassword/**").permitAll()
            	// Add any other truly public pages or static resources here

                // 2. DASHBOARD ACCESS: All authenticated users (Admin, Manager, User) can access the dashboard
                // This specific rule MUST come before more general /admin/** rules if you want Managers/Users to access dashboard
                .requestMatchers("/ticketingGateway/admin/users/dashboard").hasAnyAuthority("ADMIN", "MANAGER", "USER")

                // 3. ADMIN-SPECIFIC ACCESS: Only users with ADMIN authority can access /admin/** paths (excluding the dashboard if specified above)
                .requestMatchers("/admin/**").hasAuthority("ADMIN") // This will cover paths like /admin/users, /admin/users/create, etc.
                .requestMatchers("/ticketingGateway/admin/**").hasAuthority("ADMIN") // If these are also purely admin-specific

                // 4. MANAGER-SPECIFIC ACCESS: Users with ADMIN or MANAGER authority can access manager-related paths
                .requestMatchers("/ticketingGateway/managers/**").hasAnyAuthority("ADMIN", "MANAGER")

                // 5. USER-SPECIFIC ACCESS: Users with ADMIN, MANAGER, or USER authority can access user-related paths
                .requestMatchers("/ticketingGateway/users/**").hasAnyAuthority("ADMIN", "MANAGER", "USER")

                // 6. CATCH-ALL: Any other request that hasn't been explicitly permitted or restricted, requires authentication
                .anyRequest().authenticated()
                .and()
            .exceptionHandling()
                .accessDeniedPage("/accessDeniedPage") // Redirects here if a user tries to access a URL they don't have authority for
                .and()
            .formLogin()
                .loginPage("/login") // Custom login page
                .defaultSuccessUrl("/ticketingGateway/admin/users/dashboard", true) // Redirect all successful logins to the dashboard
                .permitAll() // Permit all to the login process itself
            .and()
            .logout()
                .logoutSuccessUrl("/login") // Redirect to home/login page after logout
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll(); // Permit all to the logout process

        return http.build();
    }
	

}
