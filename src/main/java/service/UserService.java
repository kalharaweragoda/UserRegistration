package service;


import dto.User;
import entity.UserEntity;

public interface UserService {
    void registerUser(User userDTO);

    boolean isEmailExists(String email);

    UserEntity authenticate(String email, String password);


}
