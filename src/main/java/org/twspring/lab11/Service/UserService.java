package org.twspring.lab11.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.twspring.lab11.Api.ApiException;
import org.twspring.lab11.Model.User;
import org.twspring.lab11.Repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    //Basic GET
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()){
            throw new ApiException("No users found");
        }
        return users;
    }

    //Basic POST
    public void addUser(User user) {
        userRepository.save(user);
    }

    //Basic PUT
    public void updateUser(Integer user_id, User user) {
        User u = userRepository.findUserByUser_id(user_id);
        if (u == null){
            throw new ApiException("User not found");
        }
        u.setUsername(user.getUsername());
        u.setPassword(user.getPassword());
        u.setEmail(user.getEmail());
        userRepository.save(u);

    }
    //Basic DELETE
    public void deleteUser(Integer user_id) {
        User u = userRepository.findUserByUser_id(user_id);
        if (u == null){
            throw new ApiException("User not found");
        }
        userRepository.delete(u);
    }
}
