package com.fatec.controle_financeiro.controllers;
 
import java.util.ArrayList;
import java.util.List;
 
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import com.fatec.controle_financeiro.entities.Client;
 
@RestController
@RequestMapping("/api/client")
public class ClientController {
 
    private List<Client> clientes = new ArrayList<>();
    private int proximoID = 1;
 
    //CREATE
    @PostMapping("")
    public ResponseEntity<Client> createClient(@RequestBody Client cliente) {
        cliente.setId(proximoID++);
        clientes.add(cliente);
       
        return new ResponseEntity<>(cliente, HttpStatus.CREATED);
    }
 
    //READ
    @GetMapping()
    public ResponseEntity<List<Client>> getAllClient() {  
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }
 
    @GetMapping("{id}")
    public ResponseEntity<Client> getByID(@PathVariable int id) {
       
        for (Client client : clientes) {
            if (client.getId() == id) {
                return new ResponseEntity<>(client, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
   
    //UPDATE
    @PutMapping("{id}")
    public ResponseEntity<Client> updateClient(@PathVariable int id, @RequestBody Client entity) {
       
        for (Client client : clientes) {
            if (client.getId()==id) {
                client.setNome(entity.getNome());
 
                return new ResponseEntity<>(client, HttpStatus.OK);
            }
        }
       
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
 
    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Client> deleteClient(@PathVariable int id){
        for (Client client : clientes) {
            if (client.getId()==id) {
                clientes.remove(client);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
   
}
 