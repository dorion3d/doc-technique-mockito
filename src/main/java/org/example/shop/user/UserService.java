package org.example.shop.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class UserService {
    private final UserClient userClient;

    public User getUserFromId(UUID id) {
        try {
            return userClient.getUser(id);
        } catch (Exception e) {
            return null;
        }
    }

    public void syncUser(User user) {
        try {
            userClient.syncUser(user);
        } catch (Exception e) {
            throw new UnknownError("Unable to sync user");
        }
    }
}
