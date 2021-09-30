package service.interfaces;

import model.User;

import java.util.List;

public interface IUserService {

    boolean insertNewUser(User user);

    boolean deleteUser(User user);

    boolean updateUser(User user);

    List<User> findAllUser();

    User findUserById(int userId);
}
