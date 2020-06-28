package org.d11.rest.controller;

import java.security.Principal;

import org.d11.rest.api.Endpoint;
import org.d11.rest.security.AuthenticationRequest;
import org.d11.rest.security.AuthenticationResponse;
import org.d11.rest.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AuthenticationController extends D11RestController {

    private AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @ResponseBody
    @GetMapping("/hello")
    public String hello(Principal user) {
        return "Hello " + user.getName() + "!";
    }

    @PostMapping(Endpoint.AUTHENTICATE)
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        Authentication authentication = this.authenticationService.authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        AuthenticationResponse authenticationResponse = new AuthenticationResponse(authentication.getCredentials().toString());
        return ResponseEntity.ok(authenticationResponse);
    }

}
