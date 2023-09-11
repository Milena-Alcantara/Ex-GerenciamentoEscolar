package edu.milenaalcantara.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
        private static final String url = "jdbc:postgresql://localhost:5432/gerenciamentoEscolardb";
        private static final String user = "postgres";
        private static final String password = "8235";
        public static Connection connect() {
            Connection conn = null;
            try {
                conn = DriverManager.getConnection(url, user, password);
                System.out.println("Connected to the PostgreSQL server successfully.");
                return conn;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

