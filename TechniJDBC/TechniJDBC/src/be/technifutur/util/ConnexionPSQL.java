package be.technifutur.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionPSQL {

    private static final String DB_URL = "jdbc:postgresql://localhost/technifuturJava";
    private static final String USER = "postgres";
    private static final String PWD = "1234";

    private static Connection instance;

    private ConnexionPSQL() {
    }

    public static Connection getInstance() throws SQLException {
        if (instance == null) {
            instance = DriverManager.getConnection(DB_URL, USER, PWD);
        }

        return instance;
    }
}
