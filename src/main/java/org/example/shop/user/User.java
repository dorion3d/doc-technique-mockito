package org.example.shop.user;

import lombok.*;
import org.example.shop.license.License;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Getter
@Setter
public class User {
    private final UUID id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private List<License> licenses;

    public void addLicense(License license) {
        if (licenses == null){
            licenses = new ArrayList<>();
        }
        licenses.add(license);
    }
}
