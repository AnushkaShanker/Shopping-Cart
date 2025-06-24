package crud.interfaces;

import entity.User;

public interface UserDAOInterface {
    User getUserById(int userId);
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(int userId);
}
