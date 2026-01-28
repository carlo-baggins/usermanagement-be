package net.carlo.usermanagement_be.auth.service;

import net.carlo.usermanagement_be.entity.Account;
import net.carlo.usermanagement_be.auth.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private AccountRepository repository;
    @Autowired
    @Lazy
    private PasswordEncoder encoder;




    // Method to load user details by username (email)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch user from the database by email (username)
        Optional<Account> userInfo = repository.findByEmail(username);

        if (userInfo.isEmpty()) {
            throw new UsernameNotFoundException("User not found with email: " + username);
        }

        // Convert UserInfo to UserDetails (UserInfoDetails)
        Account user = userInfo.get();
        UserDetailsImpl userDetails = new UserDetailsImpl(user);
        return new User(user.getEmail(), user.getPassword(), userDetails.getAuthorities());
    }

    // Add any additional methods for registering or managing users
    public String addUser(Account account) {
        // Encrypt password before saving
        account.setPassword(encoder.encode(account.getPassword()));
        repository.save(account);
        return "User added successfully!";
    }
}