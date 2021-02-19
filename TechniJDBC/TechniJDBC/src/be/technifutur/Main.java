package be.technifutur;

import java.sql.*;

public class Main {

    private static void affiche(String message) {
        System.out.println(message);
    }

    private static void arret(String message) {
        System.err.println(message);
        System.exit(99);
    }

    public static void main(String[] args) {

        // Ne plus le faire vu qu'il est chargé automatiquement
//        try {
//            Class.forName("org.postgresql.Driver");
//            // MySQL com.mysql.jdbc.Driver
//        } catch (ClassNotFoundException e) {
//
//        }

        affiche("Connexion à la DB");
        String DBUrl = "jdbc:postgresql://localhost/technifuturJava";
        // MySQL jdbc:mysql://localhost:3306/technifuturJava

        // Création de l'object de connection
        try (Connection connection = DriverManager.getConnection(DBUrl, "postgres", "1234")) {
            affiche("Connecté à la DB avec succès ...");
            afficheVoitures(connection);
         //   int nbMiseAJour = insererVoiture(connection, "Porsche 911 GT3 RS", 259459D);
         //   affiche("Nombre d'entrées mise à jour : " + nbMiseAJour);
         //   afficheVoitures(connection);
            rechercherVoitureParId(connection, 3);

        } catch (SQLException e) {
            arret(e.getMessage());
        }


    }

    private static void afficheVoitures(Connection connection) throws SQLException {
        // Création de l'objet de requête
        Statement statement = connection.createStatement();
        // Exécution de la requête et récupération du résultat
        ResultSet resultSet = statement.executeQuery("SELECT * FROM public.car");
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int nbCols = resultSetMetaData.getColumnCount();

        // Affichage du résultat
        while (resultSet.next()) {
            System.out.printf("%-10.10s %30.30s %30.30s EUR%n",
                    resultSet.getInt("ID"),
                    resultSet.getString("Model"),
                    resultSet.getDouble("Price"));

            // Affichage via le resultSetMetaData
//            for (int i = 1; i <= nbCols ; i++) {
//                System.out.print(resultSet.getString(i));
//                System.out.println();
//            }

        }

    }

    private static int insererVoiture(Connection connection, String model, Double price) throws SQLException {
        affiche("Insertion d'une nouvelle voiture en DB");
        // Création de l'objet de requête
        Statement statement = connection.createStatement();
        // Execution de la requête
        return statement.executeUpdate("INSERT INTO public.car (\"Model\", \"Price\") VALUES ('" + model + "', " + price + ")");
    }

    private static void rechercherVoitureParId(Connection connection, int id) throws SQLException {
        affiche("Recherche d'une voiture en DB par ID");
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM public.car WHERE \"ID\" = ?");
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()) {
            System.out.printf("%-10.10s %30.30s %30.30s EUR%n",
                    resultSet.getInt("ID"),
                    resultSet.getString("Model"),
                    resultSet.getDouble("Price"));
        }
    }
}
