package be.technifutur.mapper;

import be.technifutur.dto.Carburant;
import be.technifutur.dto.Moteur;
import be.technifutur.dto.Voiture;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Mapper {

    public static Voiture toDtoVoiture(ResultSet resultSet) throws SQLException {

        int id = resultSet.getInt("CarID");
        String model = resultSet.getString("Model").trim();
        Double price = resultSet.getDouble("Price");
        Moteur moteur = new Moteur(
                resultSet.getInt("EngineID"),
                resultSet.getInt("Cylindree"),
                Carburant.valueOf(resultSet.getString("Carburant")),
                resultSet.getInt("Puissance"));

        return new Voiture(id, model, price, moteur);
    }

    public static Moteur toDtoMoteur(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("EngineID");
        int cylindree = resultSet.getInt("Cylindree");
        int puissance = resultSet.getInt("Puissance");
        Carburant carburant = Carburant.valueOf(resultSet.getString("Carburant"));
        return new Moteur(id, cylindree, carburant, puissance);
    }

}
