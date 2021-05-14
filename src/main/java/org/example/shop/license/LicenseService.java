package org.example.shop.license;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class LicenseService implements LicenseServiceInterface {
    private final LicenseClient licenseClient;

    @Override
    public License licenseUser(UUID productID, UUID userID) {
        UUID licenseId = licenseClient.createLicense(productID, userID);
        return new License(licenseId, productID, LicenseState.ENABLED);
    }
}
