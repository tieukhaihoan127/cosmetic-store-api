package com.vincent.beauty_shop.service.transaction;

import com.vincent.beauty_shop.entity.InventoryTransaction;
import com.vincent.beauty_shop.entity.PurchaseOrder;
import com.vincent.beauty_shop.repository.InventoryTransactionRepository;
import com.vincent.beauty_shop.repository.PurchaseOrderItemRepository;
import com.vincent.beauty_shop.repository.PurchaseOrderRepository;
import com.vincent.beauty_shop.request.transaction.InventoryTransactionCreateRequest;
import com.vincent.beauty_shop.request.transaction.InventoryTransactionGetRequest;
import com.vincent.beauty_shop.request.transaction.PurchaseOrderCreateRequest;
import com.vincent.beauty_shop.response.transaction.ProductStoreDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {
    private InventoryTransactionRepository inventoryTransactionRepository;
    private PurchaseOrderRepository purchaseOrderRepository;
    private PurchaseOrderItemRepository purchaseOrderItemRepository;

    public TransactionServiceImpl(InventoryTransactionRepository inventoryTransactionRepository, PurchaseOrderRepository purchaseOrderRepository, PurchaseOrderItemRepository purchaseOrderItemRepository) {
        this.inventoryTransactionRepository = inventoryTransactionRepository;
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.purchaseOrderItemRepository = purchaseOrderItemRepository;
    }


    @Override
    public List<ProductStoreDto> getAllProductsFromStore(Long storeId) {
        return List.of();
    }

    @Override
    public List<PurchaseOrder> getAllPurchaseOrdersFromStore(Long storeId, LocalDateTime start, LocalDateTime end, String status) {
        return List.of();
    }

    @Override
    public PurchaseOrder getPurchaseOrderDetail(Long orderId) {
        return null;
    }

    @Override
    public PurchaseOrder createPurchaseOrder(PurchaseOrderCreateRequest purchaseOrderCreateRequest) {
        return null;
    }

    @Override
    public PurchaseOrder updatePurchaseOrderStatus(Long id) {
        return null;
    }

    @Override
    public InventoryTransaction getInventoryTransactionDetail(Long id) {
        return null;
    }

    @Override
    public InventoryTransaction createInventoryTransaction(InventoryTransactionCreateRequest inventoryTransactionCreateRequest) {
        return null;
    }

    @Override
    public List<InventoryTransaction> getAllInventoryTransactions(InventoryTransactionGetRequest inventoryTransactionGetRequest) {
        return List.of();
    }

    @Override
    public int getCurrentStockByProduct(Long productId) {
        return 0;
    }

    @Override
    public int getCurrentStockByProductVariant(Long variantId) {
        return 0;
    }
}
