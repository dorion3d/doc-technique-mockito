package org.example.shop;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ClientConfiguration {
    @Bean("userClientRestTemplate")
    public RestTemplate getUserClientRestTemplate() {
        return new RestTemplateBuilder()
                .rootUri("https://user.com")
                .build();
    }

    @Bean("licenseClientRestTemplate")
    public RestTemplate getLicenseClientRestTemplate() {
        return new RestTemplateBuilder()
                .rootUri("https://license.com")
                .build();
    }
}
