package com.vincent.beauty_shop.service.store;

import com.vincent.beauty_shop.entity.Store;
import com.vincent.beauty_shop.exception.DeleteForeignKeyException;
import com.vincent.beauty_shop.exception.ResourceNotFoundException;
import com.vincent.beauty_shop.mapper.StoreMapper;
import com.vincent.beauty_shop.repository.StoreRepository;
import com.vincent.beauty_shop.request.store.StoreCreateRequest;
import com.vincent.beauty_shop.request.store.StoreUpdateRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StoreServiceImpl implements StoreService{
    private StoreRepository storeRepository;
    private StoreMapper storeMapper;

    public StoreServiceImpl(StoreRepository storeRepository, StoreMapper storeMapper) {
        this.storeRepository = storeRepository;
        this.storeMapper = storeMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }

    @Override
    public Store createStore(StoreCreateRequest storeRequest) {
        Store store = Store.builder().name(storeRequest.getName()).hotline(storeRequest.getHotline()).city(storeRequest.getCity()).ward(storeRequest.getWard()).district(storeRequest.getDistrict()).address(storeRequest.getAddress()).openingHour(storeRequest.getOpeningHour()).closingHour(storeRequest.getClosingHour()).image(storeRequest.getImage()).build();
        return storeRepository.save(store);
    }

    @Override
    @Transactional(readOnly = true)
    public Store getStore(Long id) {
        return storeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Store not found with id " + id));
    }

    @Override
    public Store updateStore(Long id, StoreUpdateRequest storeRequest) {
        Store store = storeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Store not found with id " + id));
        storeMapper.updateStoreFromDto(storeRequest, store);
        return storeRepository.save(store);
    }

    @Override
    public void deleteStore(Long id) {
        Store store = storeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Store not found with id " + id));

        if(!store.getAccounts().isEmpty()) {
            throw new DeleteForeignKeyException("Cannot delete store because it has accounts.");
        }
        else if(!store.getProductStores().isEmpty()) {
            throw new DeleteForeignKeyException("Cannot delete store because it has product stores.");
        }
        else if(!store.getBillPickups().isEmpty()) {
            throw new DeleteForeignKeyException("Cannot delete store because it has bills.");
        }
        else if(!store.getInvertories().isEmpty()) {
            throw new DeleteForeignKeyException("Cannot delete store because it has inventories.");
        }

        storeRepository.delete(store);
    }
}
