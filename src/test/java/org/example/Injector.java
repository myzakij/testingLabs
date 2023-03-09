package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Injector {
    public static void executeSqlScript(String sqlScript) throws Exception {
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/testing", "postgres", "galik240101");
            stmt = conn.createStatement();

            String[] queries = sqlScript.split(";");

            for (String query : queries) {
                stmt.executeUpdate(query.trim());
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
}
