package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = Util.getConnectionJDBC();
             Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS Users (id INT PRIMARY KEY AUTO_INCREMENT,name VARCHAR(10),lastName VARCHAR(10),age INT )");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.getConnectionJDBC();
             Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS Users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getConnectionJDBC();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users VALUES(id,?,?,?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем – " + name + " был добавлен в базу данных.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void removeUserById(long id) {
        try (Connection connection = Util.getConnectionJDBC();
             Statement statement = connection.createStatement()) {
            statement.execute(" DELETE FROM users WHERE id =" + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> usersL = new ArrayList<>();
        try (Connection connection = Util.getConnectionJDBC();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from users;");
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));
                usersL.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return usersL;
        }
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getConnectionJDBC();
             Statement statement = connection.createStatement()) {
            statement.execute("DELETE from users;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
