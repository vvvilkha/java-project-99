package hexlet.code.utils;

import hexlet.code.database.entity.User;
import hexlet.code.database.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserUtils {

    private final UserRepository userRepository;

    public UserUtils(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getCurrentUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        var email = authentication.getName();
        return userRepository.findByEmail(email).orElse(null);
    }

    public boolean isAuthor(Long id) {
        User currentUser = getCurrentUser();
        if (currentUser == null) {
            return true;
        }

        return currentUser.getId() != null && currentUser.getId() == id;
    }
}
