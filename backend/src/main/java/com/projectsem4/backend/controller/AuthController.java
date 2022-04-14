package com.projectsem4.backend.controller;

import com.projectsem4.backend.Payload.Request.LoginRequest;
import com.projectsem4.backend.Payload.Request.SignupRequest;
import com.projectsem4.backend.Payload.Response.JwtResponse;
import com.projectsem4.backend.Payload.Response.MessageResponse;
import com.projectsem4.backend.Security.JWT.JwtUtils;
import com.projectsem4.backend.Security.Services.UserDetailsImpl;
import com.projectsem4.backend.entity.*;
import com.projectsem4.backend.repository.AccountRepo;
import com.projectsem4.backend.repository.RoleRepository;
import com.projectsem4.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*" , maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    AccountRepo accountRepo;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest)
    {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(
                jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                userDetails.getPhone(),
                userDetails.getAccountType().getName().toString(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest)
    {
        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error : Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error : Email is already in use"));
        }

        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                signUpRequest.getPhone(),
                encoder.encode(signUpRequest.getPassword()));


        String accountType = signUpRequest.getAccountType();


        if(accountType == null)
        {
            AccountType accountType1 = accountRepo.findByName(EAccount.ACCOUNT_NORMAL)
                    .orElseThrow(() -> new RuntimeException(("Error: Account is not found")));
            user.setAccountType(accountType1);
        }
        else
        {
            switch (accountType) {
                case "admin" :
                    AccountType accountType1 = accountRepo.findByName(EAccount.ACCOUNT_ADMIN)
                            .orElseThrow(() -> new RuntimeException(("Error: Account is not found")));
                    user.setAccountType(accountType1);
                    break;
                case "normal" :
                    AccountType accountCust = accountRepo.findByName(EAccount.ACCOUNT_NORMAL)
                            .orElseThrow(() -> new RuntimeException(("Error: Account is not found")));
                    user.setAccountType(accountCust);
                    break;
                case "vip" :
                    AccountType accountVIP = accountRepo.findByName(EAccount.ACCOUNT_VIP)
                            .orElseThrow(() -> new RuntimeException(("Error: Account is not found")));
                    user.setAccountType(accountVIP);
                    break;
            }
        }



        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();
        if(strRoles == null) {
            Role modRole = roleRepository.findByName(ERole.ROLE_CUSTOMER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(modRole);
        }
        else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin" :
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                        roles.add(adminRole);
                        break;
                    case "customer" :
                        Role modRole = roleRepository.findByName(ERole.ROLE_CUSTOMER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                        roles.add(modRole);
                        break;
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registerd successfully"));
    }
}
