package org.example.shop.product;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class AddProductRequest {
    final private UUID productId;
    final private UUID userId;
}
