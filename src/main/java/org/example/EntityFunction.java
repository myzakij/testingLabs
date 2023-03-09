package org.example;

import java.sql.SQLException;

public class EntityFunction {

    private static Database database;

    static {
        database = Database.getObjectDatabaseControl();
    }

    public static Boolean registration(String login, String password) {
        if (Valid.checkLogin(login) && Valid.checkPassword(password)) {
            Entity entity = new Entity(login, password);
            try {
                return database.addUser(entity);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            return false;
        }
    }

    public static Boolean login(String login, String password) {
        if (Valid.checkLogin(login) && Valid.checkPassword(password)) {
            Entity user = null;
            try {
                user = database.getUserByLogin(login);
                if (user == null)
                    return false;
                if (user.getPassword().equals(password) && !user.getOnline()) {
                    return database.userLogin(user);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    public static Boolean logout(String login) {
        if (Valid.checkLogin(login)) {
            Entity user = null;
            try {
                user = database.getUserByLogin(login);
                if (user == null)
                    return false;
                if (user.getOnline()) {
                    return database.userLogout(user);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }
}
