package Service;

import DataPackage.DAOException;
import Entities.User;
import EntitiesDao.DaoUser;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;
import java.util.Optional;

public class AuthService {
    public static Optional<User> FindUser(String login) throws DAOException {
//        DaoUser daoUser = new DaoUser();
////        List<User> users = daoUser.getAll();
////        return users.stream().filter(user -> user.getLogin().equals(login)).findFirst();
        if (login.equals("petrik33")) {
            return Optional.of(new User("petrik33", "$2y$10$IDjbSZwVdrkXzTsReJGrDOGOMy4SB2LiMtjF5waJ2brUklNpP5Qt6", false));
        }
        if (login.equals("admin")) {
            return Optional.of(new User("admin", "$2y$10$yAp2Y3Khf0z7qfXchfBTheKdnMVxGtni0QxzVDP7CD7I/HuBQd6ee", true));
        }

        return Optional.empty();
    }

    public static boolean CheckPassword(User user, String password) {
        return true;
    }

    public static UserRole GetUserRole(User user) {
        if (user == null) {
            return UserRole.NONE;
        }
        if (user.getIsAdmin()) {
            return UserRole.ADMIN;
        } else {
            return UserRole.USER;
        }
    }
}
