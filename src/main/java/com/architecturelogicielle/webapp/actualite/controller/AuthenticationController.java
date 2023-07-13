package com.architecturelogicielle.webapp.actualite.controller;

import com.architecturelogicielle.webapp.actualite.auth.AuthenticationResponse;
import com.architecturelogicielle.webapp.actualite.repository.AuthenticationProxy;

import lombok.RequiredArgsConstructor;
import com.architecturelogicielle.webapp.actualite.model.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationProxy service;


    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) throws Exception {
        AuthenticationResponse authenticationResponse = service.authenticateUser(request);
        List<Role> roles = service.getUserRoles(authenticationResponse.getAccessToken());
        authenticationResponse.setRoles(roles.stream().map(Role::name).collect(Collectors.toList()));

        boolean isAdmin = roles.contains(Role.ADMIN);
        authenticationResponse.setAdmin(isAdmin);
        
        return ResponseEntity.ok(authenticationResponse);
    }
    
    @GetMapping("/logout")
    public RedirectView logout(@RequestParam String accessToken) {
        service.logout(accessToken);
        return new RedirectView("/");
    }

}
