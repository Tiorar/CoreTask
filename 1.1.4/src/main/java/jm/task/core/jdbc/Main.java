package jm.task.core.jdbc;

import com.mysql.fabric.jdbc.FabricMySQLDriver;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import java.util.Iterator;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;




public class Main {

    public static void main(String[] args) throws SQLException {

        new UserServiceImpl().createUsersTable();

        new UserServiceImpl().saveUser("Владимир", "Зеленский", (byte) 37);
        new UserServiceImpl().saveUser("Владимир", "Путин", (byte) 41);
        new UserServiceImpl().saveUser("Олег", "Монгол", (byte) 69);
        new UserServiceImpl().saveUser("Скибиди", "Туалет", (byte) 111);

        new UserServiceImpl().removeUserById(3);


       List<User> arr = new UserServiceImpl().getAllUsers();
       arr.stream().forEach(user-> System.out.println(user.toString()));

        new UserServiceImpl().cleanUsersTable();
       new UserServiceImpl().dropUsersTable();


    }
}
