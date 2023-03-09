package org.example;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;

import java.sql.SQLException;


public class DatabaseTest {
    private static Database database = Database.getObjectDatabaseControl();
//    @BeforeAll
//    public static void start() {
//        try {
//            Injector.executeSqlScript("CREATE TABLE users " +
//                    "(id serial primary key, " +
//                    "login varchar(35) unique, " +
//                    "password varchar(35), " +
//                    "is_online boolean);");
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

    @Test
    public void userExistNeg() {
        try {
            Assertions.assertNotNull(database.getUserByLogin("galikgal"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void addUser() {
        Assertions.assertTrue(EntityFunction.registration("galikgal", "123456"));
    }

    @Test
    public void userExistPos() {
        try {
            Assertions.assertNotNull(database.getUserByLogin("galikgal"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void dbConnecting() {
        try {
            Assertions.assertTrue(database.isConnect());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void logoutPos() {
        EntityFunction.login("galikgal", "123456");
        Assertions.assertTrue(EntityFunction.logout("galikgal"));
    }

    @Test
    public void logoutNeg() {
        Assertions.assertFalse(EntityFunction.logout("galikgal"));
    }

    @AfterAll
    public static void finish() {
        try {
            Injector.executeSqlScript("DROP TABLE users;");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}