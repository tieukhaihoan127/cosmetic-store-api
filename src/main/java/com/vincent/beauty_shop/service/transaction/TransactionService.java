package com.vincent.beauty_shop.service.transaction;

import com.vincent.beauty_shop.response.transaction.ProductStoreDto;

import java.util.List;

public interface TransactionService {
    List<ProductStoreDto> getAllProductsFromStore(Long storeId);


}
