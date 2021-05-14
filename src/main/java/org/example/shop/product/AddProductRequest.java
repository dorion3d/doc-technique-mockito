package org.example.shop.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.shop.exceptions.BadRequest;
import org.example.shop.exceptions.ShopException;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class AddProductRequest {
    final private UUID productId;
    final private UUID userId;

    public void verify() throws ShopException {
        if (productId == null) {
            throw new BadRequest("Product ID is null");
        }
        if (userId == null) {
            throw new BadRequest("User ID is null");
        }
    }
}
