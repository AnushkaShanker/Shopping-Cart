import crud.UserDAO;
import crud.interfaces.UserDAOInterface;
import service.UserService;
import view.UserView;

public class Main {
    public static void main(String[] args) {
        UserDAOInterface userDAO = new UserDAO();
        UserService userService = new UserService(userDAO);
        UserView userView = new UserView(userService);
        
        userView.showMenu();
    }
}
