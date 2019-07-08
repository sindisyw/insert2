/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AssetManagement.AssetManagement.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

/**
 *
 * @author Okala
 */
@Configuration
public class CustomLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {
        String targetUrl = determineTargetUrl(authentication);

        if (response.isCommitted()) {
            return;
        }

        RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    protected String determineTargetUrl(Authentication authentication) {
        String url = "/login?error";

        //Fetch the roles drom Authentication object
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> roles = new ArrayList<>();
        for (GrantedAuthority a : authorities) {
            roles.add(a.getAuthority());
        }
        
        //check user role and decide the direct URL
        if (roles.contains("Admin")) {
            url = "/home";
        } else if (roles.contains("Employee")) {
            url = "/employee";
        } else if (roles.contains("Department Manager")) {
            url = "/deptmanager";
        } else if (roles.contains("Division Manager")) {
            url = "/divmanager";
        } else if (roles.contains("Admin") && roles.contains("Department Manager")) {
            url = "/admindept";
        } else if (roles.contains("Admin") && roles.contains("Division Manager")) {
            url = "/admindiv";
        } else if (roles.contains("Manager Department") && roles.contains("Division Manager")) {
            url = "/depdiv";
        }         
        return url;
    }
}

