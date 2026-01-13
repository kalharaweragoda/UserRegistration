package repository;

import entity.UserEntity;

public interface UserDao {
    void save(UserEntity user);

    UserEntity findByEmail(String email);

    UserEntity findByEmailAndPassword(String email, String password);
}
