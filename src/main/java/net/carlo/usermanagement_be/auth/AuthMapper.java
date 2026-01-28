package net.carlo.usermanagement_be.auth;

import net.carlo.usermanagement_be.dto.AccountDto;
import net.carlo.usermanagement_be.entity.Account;

public class AuthMapper {

    public static AccountDto fromUserEntity2AuthDto(Account entity) {
        return new AccountDto(entity.getEmail(),entity.getId(), entity.getRoles());
    }

    public static AccountDto fromUserEntity2AccountDto(Account entity) {
        return new AccountDto(entity.getId(), entity.getEmail(), entity.getRoles(),entity.getName(), entity.getSurname(), entity.getPhone());
    }


}
