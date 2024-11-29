package com.fatec.controle_financeiro.controllers;
 
import java.util.ArrayList;
import java.util.List;
 
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
 
import com.fatec.controle_financeiro.entities.User;
import org.springframework.web.bind.annotation.PutMapping;
 
 
 
 
 
@RestController
@RequestMapping("/api/user")
public class UserController {
 
    private List<User> usuarios = new ArrayList<>();
    private int proximoId = 1;
   
    /* CRUD - Create, Read, Update e Delete */
 
    //CREATE    
    @PostMapping("")
    public ResponseEntity<User> createUser(@RequestBody User usuario){
        usuario.setId(proximoId++);
        //usuario=cliente
        //usuarios=clientes
        //User=Cliente
        usuarios.add(usuario);
 
        return new ResponseEntity<>(usuario, HttpStatus.CREATED);
    }
 
    //READ
 
    @GetMapping()
    public ResponseEntity<List<User>> getAllUser(){
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }
   
    @GetMapping("{id}")
        public ResponseEntity<User> getById(@PathVariable int id) {
 
            for (User user : usuarios) {
                if (user.getId() == id) {
                    return new ResponseEntity<>(user, HttpStatus.OK);
                }
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
   
    //UPDATE
    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User entity) {
       
        //Percorre a lista de usuários para encontrar o usuário com o IF correspondente
        for (User user : usuarios) {
            if (user.getId() == id) {
                user.setName(entity.getName());
                user.setAge(entity.getAge());
 
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
 
    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable int id){
        for (User user : usuarios) {
            if (user.getId()==id) {
                usuarios.remove(user);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
 
}