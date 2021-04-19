package org.example.shop.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.example.shop.license.License;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class User {
    private String firstName;
    private String lastName;
    private String email;
    private List<License> licenses;

    public void addLicense(@NonNull License license) {
        licenses.add(license);
    }
}
