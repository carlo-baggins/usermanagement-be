package net.carlo.usermanagement_be.auth.controller;


import net.carlo.usermanagement_be.auth.AuthMapper;
import net.carlo.usermanagement_be.auth.dto.AuthRequest;
import net.carlo.usermanagement_be.auth.dto.AuthResponse;
import net.carlo.usermanagement_be.auth.dto.PingMessage;
import net.carlo.usermanagement_be.auth.dto.RegistrationResponseDto;
import net.carlo.usermanagement_be.entity.Account;
import net.carlo.usermanagement_be.auth.repository.AccountRepository;
import net.carlo.usermanagement_be.auth.service.JwtService;
import net.carlo.usermanagement_be.auth.service.UserDetailsServiceImpl;
import net.carlo.usermanagement_be.message.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = {"http://localhost:5001","http://localhost:5173"})
@RequestMapping(path = "/auth")
public class AuthController {

    private UserDetailsServiceImpl service;
    private AuthenticationManager authenticationManager;
    private JwtService jwtService;
    private AccountRepository accountRepository;

    @Autowired
    public AuthController(UserDetailsServiceImpl service, AuthenticationManager authenticationManager, JwtService jwtService, AccountRepository accountRepository) {
        this.service = service;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.accountRepository = accountRepository;
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @PostMapping("/addNewUser")
    public ResponseEntity<RegistrationResponseDto> addNewUser(@RequestBody Account account) {
        try {
            if ( account.getRoles() == null) {
                account.setRoles("ROLE_USER");
            }
            RegistrationResponseDto resp = new RegistrationResponseDto(service.addUser(account));
            return new ResponseEntity<>(resp,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RegistrationResponseDto(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Removed the role checks here as they are already managed in SecurityConfig

    @PostMapping(value = "/generateToken",produces = "application/json")
    public ResponseEntity<AuthResponse> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (AuthenticationException e) {
            AuthResponse resp =  new AuthResponse(null,403,e.getMessage(), null);
            System.out.println("Error: " + e.getMessage());
            return new ResponseEntity<>(resp, HttpStatus.UNAUTHORIZED);
        }
        boolean isAutenticated = authentication.isAuthenticated();

        if (isAutenticated) {

            String token =  jwtService.generateToken(authRequest.getUsername());
            try {
                Optional<Account> userOpt = accountRepository.findByEmail(authRequest.getUsername());
                if ( userOpt.isEmpty()) {
                    return new ResponseEntity<>(new AuthResponse(null,401,"User not fount, invalid user request!", null), HttpStatus.UNAUTHORIZED);
                }
                Account user = userOpt.get();
                return new ResponseEntity<>(new AuthResponse(token,200,"OK", AuthMapper.fromUserEntity2AccountDto(user)), HttpStatus.OK);
            } catch (Exception e) {
                System.out.println("Error findByEmail: " + e.getMessage());
                throw e;
            }
        } else {
            return new ResponseEntity<>(new AuthResponse(null,401,"User not fount, invalid user request!", null), HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/ping")
    public Response<PingMessage> ping() {
        return new Response<>(new PingMessage("OK"),"OK",true,200);
    }
}
