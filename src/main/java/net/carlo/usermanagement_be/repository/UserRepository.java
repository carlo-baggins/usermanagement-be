package net.carlo.usermanagement_be.repository;


import net.carlo.usermanagement_be.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Account,Integer> {
    Account findByEmail(String email);

}
