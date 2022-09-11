package fuad.hajiyev.abc_app.services;


import fuad.hajiyev.abc_app.entities.User;
import fuad.hajiyev.abc_app.repos.UserRepository;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    final UserRepository userRepository;

    public List<User> getUsersFomService() {
        List<User> result = userRepository.findAll();

        return result;
    }


    public User createUserFomService(User newUser) {
        return userRepository.save(newUser);
    }

    public User getUserByIdFromService(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new NullPointerException("user not found !"));
    }

    public User updateUserFromService(Long userId, User updateUser) {

        Optional<User> result = userRepository.findById(userId);
        System.out.println(updateUser.toString());
        if (result.isPresent()) {
            result.get().setUserName(updateUser.getUserName());
            result.get().setPassword(updateUser.getPassword());
            return userRepository.save(result.get());
        }
        return null;


    }

    public User deleteOneUserFromService(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NullPointerException("user not found !"));
        userRepository.deleteById(user.getId());
        return user;
    }
}
