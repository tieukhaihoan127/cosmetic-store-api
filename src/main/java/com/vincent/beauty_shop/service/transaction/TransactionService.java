package com.vincent.beauty_shop.service.transaction;

import com.vincent.beauty_shop.entity.InventoryTransaction;
import com.vincent.beauty_shop.entity.PurchaseOrder;
import com.vincent.beauty_shop.request.transaction.InventoryTransactionCreateRequest;
import com.vincent.beauty_shop.request.transaction.InventoryTransactionGetRequest;
import com.vincent.beauty_shop.request.transaction.PurchaseOrderCreateRequest;
import com.vincent.beauty_shop.response.transaction.ProductStoreDto;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionService {
    List<ProductStoreDto> getAllProductsFromStore(Long storeId);

    List<PurchaseOrder> getAllPurchaseOrdersFromStore(Long storeId, LocalDateTime start, LocalDateTime end, String status);

    PurchaseOrder getPurchaseOrderDetail(Long orderId);

    PurchaseOrder createPurchaseOrder(PurchaseOrderCreateRequest purchaseOrderCreateRequest);

    PurchaseOrder updatePurchaseOrderStatus(Long id);

    InventoryTransaction getInventoryTransactionDetail(Long id);

    InventoryTransaction createInventoryTransaction(InventoryTransactionCreateRequest inventoryTransactionCreateRequest);

    List<InventoryTransaction> getAllInventoryTransactions(InventoryTransactionGetRequest inventoryTransactionGetRequest);

    int getCurrentStockByProduct(Long productId);

    int getCurrentStockByProductVariant(Long variantId);
}
