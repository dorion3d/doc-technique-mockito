package org.example.shop.license;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class License {
    private final UUID id;
    private final UUID productId;
    private final LicenseState licenseState;
}
