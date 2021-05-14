package org.example.shop.product;

import org.example.shop.exceptions.BadRequest;
import org.example.shop.exceptions.NotFound;
import org.example.shop.license.License;
import org.example.shop.license.LicenseServiceMock;
import org.example.shop.license.LicenseState;
import org.example.shop.user.User;
import org.example.shop.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductControllerTest {
    // Mock classique
    private final LicenseServiceMock licenseServiceMock = new LicenseServiceMock();
    // Mockito mock
    private final UserService userServiceMock = mock(UserService.class);
    private final ProductController underTest = new ProductController(licenseServiceMock, userServiceMock);

    private static final UUID VALID_USER_ID = UUID.randomUUID();
    private static final UUID VALID_PRODUCT_ID = UUID.randomUUID();
    private static final UUID VALID_LICENSE_ID = UUID.randomUUID();
    private static final License VALID_LICENSE = new License(VALID_LICENSE_ID, VALID_PRODUCT_ID, LicenseState.ENABLED);

    private static final User VALID_USER = new User(VALID_USER_ID, "Bob", "Bobby", "bob@email.com");
    private static final AddProductRequest VALID_REQUEST = new AddProductRequest(VALID_PRODUCT_ID, VALID_USER_ID);

    @BeforeEach
    public void setUp() {
        licenseServiceMock.reset();
    }

    @Test
    public void whenAddProduct_givenValidRequest_thenGiveUserProduct() throws Exception {
        when(userServiceMock.getUserFromId(VALID_USER_ID)).thenReturn(VALID_USER);
        licenseServiceMock.setLicenseToReturn(VALID_LICENSE);

        underTest.addProduct(VALID_REQUEST);

        verify(userServiceMock, times(1)).getUserFromId(VALID_USER_ID);
        verify(userServiceMock, times(1)).syncUser(VALID_USER);
        assertTrue(licenseServiceMock.wasCalled(1));
    }

    @Test
    public void whenAddProduct_givenInvalidRequest_thenThrowBadRequest() {
        AddProductRequest invalidRequest = new AddProductRequest(null, null);
        try {
            underTest.addProduct(invalidRequest);
        } catch (Exception e) {
            if (e instanceof BadRequest) {
                BadRequest error = (BadRequest) e;
                assertEquals(error.getMessage(), "Product ID is null");
            }
            else {
                fail();
            }
        }
    }

    @Test
    public void whenAddProduct_givenUnknownUser_thenThrowBadRequest() throws Exception {
        UUID unknownId = UUID.randomUUID();
        AddProductRequest invalidRequest = new AddProductRequest(VALID_PRODUCT_ID, unknownId);
        when(userServiceMock.getUserFromId(unknownId)).thenThrow(new NotFound("User unknown"));

        try {
            underTest.addProduct(invalidRequest);
        } catch (Exception e) {
            if (e instanceof NotFound) {
                NotFound error = (NotFound) e;
                assertEquals(error.getMessage(), "User unknown");
            }
            else {
                fail();
            }
        }
    }
}