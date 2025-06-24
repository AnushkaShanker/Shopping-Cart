package service;

import crud.interfaces.UserDAOInterface;
import entity.User;

public class UserService {
    private final UserDAOInterface userDAO;

    public UserService(UserDAOInterface userDAO) {
        this.userDAO = userDAO;
    }

    public User getUserById(int userId) {
        return userDAO.getUserById(userId);
    }

    public void addUser(User user) {
        if (validateUser(user)) {
            userDAO.addUser(user);
        } else {
            throw new IllegalArgumentException("Invalid user data");
        }
    }

    public void updateUser(User user) {
        if (validateUser(user)) {
            userDAO.updateUser(user);
        } else {
            throw new IllegalArgumentException("Invalid user data");
        }
    }

    public void deleteUser(int userId) {
        userDAO.deleteUser(userId);
    }

    private boolean validateUser(User user) {
        return user.getName() != null && !user.getName().isEmpty()
                && user.getEmail() != null && !user.getEmail().isEmpty()
                && user.getPassword() != null && !user.getPassword().isEmpty();
    }
}
