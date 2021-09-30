package service;

import dao.UserDAOImpl;
import model.User;

public class UserService {

    private UserDAOImpl userDAO = new UserDAOImpl();

    public User findUserById(int userId) {
        return userDAO.findById(User.class, userId);
    }



}
