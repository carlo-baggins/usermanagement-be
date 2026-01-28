package net.carlo.usermanagement_be.auth.repository;

import net.carlo.usermanagement_be.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByEmail(String email); // Use 'email' if that is the correct field for login
}