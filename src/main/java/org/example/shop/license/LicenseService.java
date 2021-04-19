package org.example.shop.license;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class LicenseService {
    private final LicenseClient licenseClient;

    public License licenseUser(UUID productID, UUID userID) {
        UUID licenseId = licenseClient.createLicense(productID, userID);
        return new License(licenseId, productID, LicenseState.ENABLED);
    }
}
