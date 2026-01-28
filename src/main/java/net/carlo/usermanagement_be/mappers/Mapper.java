package net.carlo.usermanagement_be.mappers;


import net.carlo.usermanagement_be.dto.AccountDto;
import net.carlo.usermanagement_be.entity.Account;

public class Mapper {

    public static AccountDto fromUserEntity2AccountDto(Account entity) {
        return new AccountDto(entity.getId(), entity.getEmail(), entity.getRoles(),entity.getName(), entity.getSurname(), entity.getPhone());
    }

    public static Account fromDtoToAccountEntity(AccountDto user) {
        return new Account(user.getId(), user.getEmail(), user.getRole(), user.getName(), user.getSurname(), user.getPhone());
    }
}
