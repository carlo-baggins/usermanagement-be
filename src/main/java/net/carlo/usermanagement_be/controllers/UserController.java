package net.carlo.usermanagement_be.controllers;

import net.carlo.usermanagement_be.dto.AccountDto;
import net.carlo.usermanagement_be.message.Response;
import net.carlo.usermanagement_be.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:5001","http://localhost:5173"} )
@RequestMapping(path = "/api/user")
public class UserController {
    @Autowired
    private UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Response<List<AccountDto>>> getAllUsers() {

       try {
           List<AccountDto> listaUtenti = userService.getAllUser();
           if ( listaUtenti != null ) {
               return ResponseEntity.ok(new Response<>(listaUtenti,"OK",true, 200));
           }
           Response<List<AccountDto>> response = new Response<>(null, "NO USER FOUND", false, -1);
           return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

       } catch (Exception e) {
           Response<List<AccountDto>> response = new Response<>(null, "Errore: " + e.getMessage(), false, 500);
           return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Response<AccountDto>> getById(@PathVariable Integer id) {
        Response<AccountDto> response;
        try {
            AccountDto user = userService.getUserById(id);
            if ( user != null ) {
                response = new Response<>(user,"OK",true,200);
                return ResponseEntity.ok(response);
            }
            response = new Response<>(null,"Nessun utente trovato",false,404);
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            response = new Response<>(null,"Errore: " + e.getMessage(),false,500);
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Response<AccountDto>> updateUser(@RequestBody AccountDto user) {
        Response<AccountDto> response;
        try {
            AccountDto updatedUser = userService.updateUser(user);

            response = new Response<>(updatedUser,"OK",true,200);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.err.println("errore update user: " + e.getMessage());
            e.printStackTrace();
            response = new Response<>(user,"Errore update: " + e.getMessage(),false,500);
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
