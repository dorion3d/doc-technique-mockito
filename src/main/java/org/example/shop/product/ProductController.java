package org.example.shop.product;

import lombok.AllArgsConstructor;
import org.example.shop.exceptions.ShopException;
import org.example.shop.license.License;
import org.example.shop.license.LicenseServiceInterface;
import org.example.shop.user.User;
import org.example.shop.user.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class ProductController {
    private final LicenseServiceInterface licenseService;
    private final UserService userService;

    @PostMapping("/products/add-product")
    public void addProduct(@RequestBody AddProductRequest request) throws Exception {
        try {
            request.verify();
            License license = licenseService.licenseUser(request.getProductId(), request.getUserId());
            User user = userService.getUserFromId(request.getUserId());
            user.addLicense(license);
            userService.syncUser(user);
        }catch (ShopException e) {
            throw e;
        } catch (Exception e) {
            throw new UnknownError("Unable to add product to user");
        }
    }
}

