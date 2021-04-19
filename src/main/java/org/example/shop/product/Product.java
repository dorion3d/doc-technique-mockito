package org.example.shop.product;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class Product {
    private final UUID id;
    private final String name;
    private final String description;
}
