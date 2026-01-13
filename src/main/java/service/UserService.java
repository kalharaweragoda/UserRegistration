package service;


import dto.User;

public interface UserService {
    void registerUser(User userDTO);

    boolean isEmailExists(String email);

    User authenticate(String email, String password);

    String getUserFirstName(String email);


    int loginUser(String email, String password);
}
