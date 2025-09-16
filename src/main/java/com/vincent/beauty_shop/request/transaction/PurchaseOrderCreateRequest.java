package com.vincent.beauty_shop.request.transaction;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrderCreateRequest {
    private String supplierName;

    @NotBlank
    private Long accountId;

    @NotBlank
    private List<PurchaseOrderItemCreateRequest> productOrderItemCreateRequests;
}
