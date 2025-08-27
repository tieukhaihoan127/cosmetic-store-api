package com.vincent.beauty_shop.controller.admin;

import com.vincent.beauty_shop.entity.Client;
import com.vincent.beauty_shop.entity.ClientAddress;
import com.vincent.beauty_shop.request.client.ClientAddressCreateRequest;
import com.vincent.beauty_shop.request.client.ClientAddressUpdateRequest;
import com.vincent.beauty_shop.request.client.ClientCreateRequest;
import com.vincent.beauty_shop.request.client.ClientUpdateRequest;
import com.vincent.beauty_shop.request.wishlist.WishlistRequest;
import com.vincent.beauty_shop.response.wishlist.WishlistDTO;
import com.vincent.beauty_shop.service.client.ClientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {
    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        return new ResponseEntity<>(clientService.getAllClients(), HttpStatus.OK);
    }

    @GetMapping("/address/{id}")
    public ResponseEntity<List<ClientAddress>> getAllClientAddresses(@PathVariable Long id) {
        return new ResponseEntity<>(clientService.getAllClientAddress(id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClient(@PathVariable Long id) {
        Client client = clientService.getClientById(id);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @GetMapping("/wishlist/{id}")
    public ResponseEntity<List<WishlistDTO>> getAllProductsFromWishlist(@PathVariable Long id) {
        return new ResponseEntity<>(clientService.getAllProductsFromWishlist(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Client> addClient(@RequestBody ClientCreateRequest request) {
        Client client = clientService.createClient(request);
        return new ResponseEntity<>(client, HttpStatus.CREATED);
    }

    @PostMapping("/address/{id}")
    public ResponseEntity<ClientAddress> addClientAddress(@PathVariable Long id, @RequestBody ClientAddressCreateRequest request) {
        ClientAddress address = clientService.createClientAddress(id, request);
        return new ResponseEntity<>(address, HttpStatus.CREATED);
    }

    @PostMapping("/wishlist")
    public ResponseEntity<WishlistDTO> addWishlist(@Valid @RequestBody WishlistRequest request) {
        WishlistDTO wishlistDto = clientService.addProductToWishlist(request);
        return new ResponseEntity<>(wishlistDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody ClientUpdateRequest request) {
        Client updatedClient = clientService.updateClient(id, request);
        return new ResponseEntity<>(updatedClient, HttpStatus.OK);
    }

    @PutMapping("/address/{id}")
    public ResponseEntity<ClientAddress>  updateClientAddress(@PathVariable Long id, @RequestBody ClientAddressUpdateRequest request) {
        ClientAddress updatedAddress = clientService.updateClientAddress(id, request);
        return new ResponseEntity<>(updatedAddress, HttpStatus.OK);
    }

    @DeleteMapping("/address/{id}")
    public ResponseEntity<String> deleteClientAddress(@PathVariable Long id) {
        clientService.deleteClientAddress(id);
        return ResponseEntity.ok("Address successfully deleted");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.ok("Client successfully deleted");
    }

    @DeleteMapping("/wishlist")
    public ResponseEntity<String> deleteProductFromWishlist(@Valid @RequestBody WishlistRequest request) {
        clientService.removeProductFromWishlist(request);
        return ResponseEntity.ok("Product successfully remove from wishlist");
    }
}
