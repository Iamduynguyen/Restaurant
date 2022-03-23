package com.restarant.backend.web.restapi;


import com.restarant.backend.domain.Account;
import com.restarant.backend.repository.AccountRepository;
import com.restarant.backend.security.jwt.JwtUtils;
import com.restarant.backend.security.services.UserDetailsImpl;
import com.restarant.backend.service.dtoinput.LoginRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.security.Principal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * REST controller for managing the current user's account.
 */
@RestController
@RequestMapping("/service")
public class AccountResource {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtils jwtUtils;
    private final PasswordEncoder encoder;
    private final AccountRepository accountRepository;

    private final Logger log = LoggerFactory.getLogger(AccountResource.class);

    public AccountResource(PasswordEncoder encoder, AccountRepository accountRepository) {
        this.encoder = encoder;
        this.accountRepository = accountRepository;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        System.out.println("alo");
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getLogin(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        Map<String, String> map = new HashMap();
        map.put("username",userDetails.getUsername());
        map.put("role",roles.get(0));
        map.put("token",jwt);
        return ResponseEntity.ok(map);
    }

    @PostMapping("/register")
    public ResponseEntity<?> createCategory(@RequestBody Account account) throws URISyntaxException {
        account.setPassword(encoder.encode(account.getPassword()));
        account.setTimereset(LocalDate.now());
        account.setCecret(UUID.randomUUID().toString());
        account.setRole("ROLE_USER");
        Account result = accountRepository.save(account);
        return ResponseEntity.ok().body(account);
    }

    @GetMapping("/account")
    public ResponseEntity<?> getAccount(Principal principal){
        Account result = accountRepository.getByLogin(principal.getName());
        return ResponseEntity.ok(principal);
    }
}
