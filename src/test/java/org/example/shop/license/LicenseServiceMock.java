package org.example.shop.license;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

public class LicenseServiceMock implements LicenseServiceInterface{
    @Getter
    private int nbTimesCalled;

    @Setter
    private License licenseToReturn;

    @Override
    public License licenseUser(UUID productID, UUID userID) {
        nbTimesCalled++;
        return licenseToReturn;
    }

    public boolean wasCalled(int _nbTimesCalled){
        return nbTimesCalled == _nbTimesCalled;
    }

    public void reset() {
        licenseToReturn = null;
        nbTimesCalled = 0;
    }
}
