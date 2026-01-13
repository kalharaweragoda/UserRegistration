package service.impl;

import dto.User;
import entity.UserEntity;
import repository.UserDao;
import service.UserService;

public class UserServiceImpl implements UserService {
    private UserDao userRepository;

    @Override
    public void registerUser(User userDTO) {

    }

    @Override
    public boolean isEmailExists(String email) {
        return false;
    }

    @Override
    public User authenticate(String email, String password) {
        return null;
    }

    @Override
    public String getUserFirstName(String email) {
        return "";
    }

    public int loginUser(String email, String password) {
        if (!email.toLowerCase().endsWith("@gmail.com")) {
            return -1;
        }



        return password.equals(User.getPassword()) ? 1 : -2;
    }
}
