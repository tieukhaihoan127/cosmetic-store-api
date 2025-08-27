package com.vincent.beauty_shop.request.wishlist;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WishlistRequest {

    @NotNull
    private Long clientId;

    @NotNull
    private Long productId;
}
