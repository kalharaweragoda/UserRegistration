package service.impl;

import dto.User;
import entity.UserEntity;
import org.modelmapper.ModelMapper;
import repository.UserDao;
import repository.impl.UserDaoImpl;
import service.UserService;

public class UserServiceImpl implements UserService {

    private final UserDao userDao = new UserDaoImpl();
    private final ModelMapper mapper = new ModelMapper();

    @Override
    public void registerUser(User userDTO) {

        if (userDao.findByEmail(userDTO.getEmail()) != null) {
            throw new RuntimeException("Email already exists");
        }

        UserEntity entity = mapper.map(userDTO, UserEntity.class);
        userDao.save(entity);
    }

    @Override
    public boolean isEmailExists(String email) {
        return userDao.findByEmail(email) != null;
    }
}
