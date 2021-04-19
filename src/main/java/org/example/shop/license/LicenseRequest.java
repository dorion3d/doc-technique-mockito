package org.example.shop.license;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class LicenseRequest {
    private final UUID productId;
    private final UUID userId;
}
