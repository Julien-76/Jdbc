package be.technifutur;

import org.postgresql.jdbc2.optional.SimpleDataSource;
import org.postgresql.jdbc3.Jdbc3PoolingDataSource;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.security.Signature;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainDataSource {

    public static void main(String[] args) {
        try {

            SimpleDataSource source = new SimpleDataSource();
            source.setServerName("localhost");
            source.setDatabaseName("technifuturJava");
            source.setUser("postgres");
            source.setPassword("1234");


            Connection connection = source.getConnection();


            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM public.engine");
            while(resultSet.next()) {
                System.out.println(resultSet.getInt("Cylindree"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
