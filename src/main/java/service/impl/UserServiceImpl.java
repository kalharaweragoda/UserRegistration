package service.impl;

import dto.User;
import entity.UserEntity;
import repository.UserDao;
import service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userRepository;

    public UserServiceImpl(UserDao userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(User userDTO) {

        UserEntity entity = new UserEntity();
        entity.setFirstName(userDTO.getFirstName());
        entity.setLastName(userDTO.getLastName());
        entity.setEmail(userDTO.getEmail());
        entity.setPassword(userDTO.getPassword());

        userRepository.save(entity);
    }

    @Override
    public boolean isEmailExists(String email) {
        return userRepository.findByEmail(email) != null;
    }

    @Override
    public User authenticate(String email, String password) {

        UserEntity entity = userRepository.findByEmail(email);

        if (entity != null && entity.getPassword().equals(password)) {
            return new User(
                    entity.getFirstName(),
                    entity.getLastName(),
                    entity.getEmail(),
                    entity.getPassword()
            );
        }
        return null;
    }

    @Override
    public String getUserFirstName(String email) {
        UserEntity entity = userRepository.findByEmail(email);
        return entity != null ? entity.getFirstName() : null;
    }

    public int loginUser(String email, String password) {

        if (!email.toLowerCase().endsWith("@gmail.com")) {
            return -1; // invalid email
        }

        UserEntity entity = userRepository.findByEmail(email);

        if (entity == null) {
            return -2;
        }

        return entity.getPassword().equals(password) ? 1 : -3;
    }
}
