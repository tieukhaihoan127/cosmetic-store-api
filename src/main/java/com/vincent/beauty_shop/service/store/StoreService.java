package com.vincent.beauty_shop.service.store;

import com.vincent.beauty_shop.entity.Store;
import com.vincent.beauty_shop.request.store.StoreCreateRequest;
import com.vincent.beauty_shop.request.store.StoreUpdateRequest;

import java.util.List;

public interface StoreService {
    List<Store> getAllStores();

    Store createStore(StoreCreateRequest storeRequest);

    Store getStore(Long id);

    Store updateStore(Long id, StoreUpdateRequest storeRequest);

    void deleteStore(Long id);
}
