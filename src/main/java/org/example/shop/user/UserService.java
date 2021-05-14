package org.example.shop.user;

import lombok.AllArgsConstructor;
import org.example.shop.exceptions.NotFound;
import org.example.shop.exceptions.ShopException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class UserService {
    private final UserClient userClient;

    public User getUserFromId(UUID id) throws ShopException {
        User user = userClient.getUser(id);
        if(user == null) {
            throw new NotFound("User not found");
        }
        return user;
    }

    public void syncUser(User user) {
        try {
            userClient.syncUser(user);
        } catch (Exception e) {
            throw new UnknownError("Unable to sync user");
        }
    }
}
