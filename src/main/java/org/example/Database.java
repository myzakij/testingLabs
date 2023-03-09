package org.example;


import java.sql.*;

public class Database {
    private Connection connection;

    private volatile static Database database;

    private Database() {
    }

    public static Database getObjectDatabaseControl() {
        if (database == null) {
            synchronized (Database.class) {
                if (database == null) {
                    database = new Database();
                }
            }
        }
        return database;
    }

    private void connectToDB() throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/testing", "postgres", "galik240101");

    }

    private void disconnectBD() throws SQLException {
        this.connection.close();
    }

    public Boolean addUser(Entity user) throws SQLException {
        connectToDB();
        PreparedStatement statement =
                connection.prepareStatement("INSERT INTO users(login, password, is_online) VALUES (?, ?, false)");
        statement.setString(1, user.getLogin());
        statement.setString(2, user.getPassword());
        int count = statement.executeUpdate();
        disconnectBD();
        return count > 0;
    }

    public Boolean userLogin(Entity user) throws SQLException {
        connectToDB();
        PreparedStatement statement =
                connection.prepareStatement("UPDATE users SET is_online = ? WHERE login = ?");
        statement.setBoolean(1, true);
        statement.setString(2, user.getLogin());
        int count = statement.executeUpdate();
        disconnectBD();
        return count > 0;
    }

    public Boolean userLogout(Entity user) throws SQLException {
        connectToDB();
        PreparedStatement statement =
                connection.prepareStatement("UPDATE users SET is_online = ? WHERE login = ?");
        statement.setBoolean(1, false);
        statement.setString(2, user.getLogin());
        int count = statement.executeUpdate();
        disconnectBD();
        return count > 0;
    }

    public Entity getUserByLogin(String login) throws SQLException {
        Entity user = null;
        connectToDB();
        PreparedStatement statement =
                connection.prepareStatement("SELECT * FROM users WHERE login = ?");
        statement.setString(1, login);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            user = new Entity(resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getBoolean(4));
        }
        disconnectBD();
        return user;
    }

    public Boolean isConnect() throws SQLException {
        return this.connection != null;
    }
}
