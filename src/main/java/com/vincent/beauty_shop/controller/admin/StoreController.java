package com.vincent.beauty_shop.controller.admin;

import com.vincent.beauty_shop.entity.Store;
import com.vincent.beauty_shop.request.store.StoreCreateRequest;
import com.vincent.beauty_shop.request.store.StoreUpdateRequest;
import com.vincent.beauty_shop.service.store.StoreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stores")
public class StoreController {
    private StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping
    public ResponseEntity<List<Store>> getAllStores() {
        return new ResponseEntity<>(storeService.getAllStores(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Store> getStore(@PathVariable Long id) {
        Store store = storeService.getStore(id);
        return new ResponseEntity<>(store, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Store> addStore(@RequestBody StoreCreateRequest request) {
        Store store = storeService.createStore(request);
        return new ResponseEntity<>(store, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Store> updateStore(@PathVariable Long id, @RequestBody StoreUpdateRequest request) {
        Store updateStore = storeService.updateStore(id, request);
        return new ResponseEntity<>(updateStore, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStore(@PathVariable Long id) {
        storeService.deleteStore(id);
        return ResponseEntity.ok("Store successfully deleted");
    }
}
