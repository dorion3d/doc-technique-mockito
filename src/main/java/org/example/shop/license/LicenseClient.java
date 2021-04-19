package org.example.shop.license;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Component
public class LicenseClient {
    private static final String CREATE_LICENSE_URL = "/licenses/create";

    private final RestTemplate restTemplate;

    public LicenseClient(@Qualifier("licenseClientRestTemplate") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public UUID createLicense(UUID productId, UUID userId) {
        final LicenseRequest request = new LicenseRequest(productId, userId);

        try {
            return restTemplate.postForObject(CREATE_LICENSE_URL, request, UUID.class);
        } catch (Exception e) {
            throw new UnknownError("Error creating and retrieving license id");
        }
    }
}
