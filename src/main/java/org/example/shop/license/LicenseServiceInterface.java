package org.example.shop.license;

import java.util.UUID;

public interface LicenseServiceInterface {
    License licenseUser(UUID productID, UUID userID);
}
