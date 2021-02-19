package be.technifutur;

import be.technifutur.service.VoitureService;
import be.technifutur.util.ConnexionPSQL;

import java.sql.*;
import java.util.Arrays;

public class MainJDBC2 {

    public static void main(String[] args) {



        try {
            Statement statement = ConnexionPSQL.getInstance().createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet = statement.executeQuery("SELECT * FROM public.engine ORDER BY \"Cylindree\"");
            resultSet.afterLast();
            while (resultSet.previous()) {

                if (resultSet.getInt("EngineID") == 1) {
                    resultSet.updateInt("Cylindree", 5000);
                    resultSet.updateRow();

                }
                System.out.println(resultSet.getInt("Cylindree"));
            }

            // Batch
            ConnexionPSQL.getInstance().setAutoCommit(false);
            Statement statement1 = ConnexionPSQL.getInstance().createStatement();

            for (int i = 0; i < 5; i++) {
                statement1.addBatch("INSERT INTO public.engine VALUES (DEFAULT, "+ i +", 'Diesel', 100 * " + i +")");
            }

            int[] miseAJours = statement1.executeBatch();
            ConnexionPSQL.getInstance().commit();
            Arrays.stream(miseAJours).forEach(System.out::println);



        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
