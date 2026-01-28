package net.carlo.usermanagement_be.service;


import net.carlo.usermanagement_be.dto.AccountDto;
import net.carlo.usermanagement_be.entity.Account;
import net.carlo.usermanagement_be.mappers.Mapper;
import net.carlo.usermanagement_be.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository repo) {
        this.userRepository = repo;
    }

    public List<AccountDto> getAllUser() {
        List<Account> listaUtenti = this.userRepository.findAll();
        return listaUtenti.stream().map(Mapper::fromUserEntity2AccountDto).toList();
    }

    public AccountDto getUserById(Integer id) {
        Account user = userRepository.getReferenceById(id);
        return Mapper.fromUserEntity2AccountDto(user);
    }

    public AccountDto updateUser(AccountDto user2update) {
        Account user = userRepository.getReferenceById(user2update.getId());
        user.setEmail(user2update.getEmail());
        user.setName(user2update.getName());
        user.setSurname(user2update.getSurname());
        user.setPhone(user2update.getPhone());
        Account updatedEntity = userRepository.save(user);
        return Mapper.fromUserEntity2AccountDto(updatedEntity);
    }
}
