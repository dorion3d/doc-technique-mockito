package org.example.shop.product;

import lombok.AllArgsConstructor;
import org.example.shop.license.License;
import org.example.shop.license.LicenseService;
import org.example.shop.user.User;
import org.example.shop.user.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class ProductController {
    private final LicenseService licenseService;
    private final UserService userService;

    @PostMapping("/products/add-product")
    public void addProduct(@RequestBody AddProductRequest request) {
        try {
            License license = licenseService.licenseUser(request.getProductId(), request.getUserId());
            User user = userService.getUserFromId(request.getUserId());
            user.addLicense(license);
            userService.syncUser(user);
        } catch (Exception e) {
            throw new UnknownError("Unable to add product to user");
        }
    }
}

