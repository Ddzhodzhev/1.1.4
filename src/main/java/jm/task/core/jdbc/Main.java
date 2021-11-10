package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService=new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Ivan","Petrov", (byte)20);
        userService.saveUser("Petr","Ivanov", (byte) 18);
        userService.saveUser("Maxim","Fomin", (byte) 13);
        userService.saveUser("Nikolay","Sidorov", (byte) 15);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
