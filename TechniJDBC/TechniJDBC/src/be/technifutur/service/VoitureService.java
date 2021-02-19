package be.technifutur.service;

import be.technifutur.dto.Voiture;
import be.technifutur.mapper.Mapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VoitureService implements Crudable<Voiture, Integer> {

    @Override
    public List<Voiture> selectAll(Connection c) throws SQLException {

        List<Voiture> output = new ArrayList<>();

        Statement statement = c.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT \"CarID\", \"Model\", \"Price\", \"EngineID\", \"Cylindree\", \"Carburant\", \"Puissance\"" +
                "FROM public.car JOIN public.engine ON \"MoteurID\" = \"EngineID\"");
        while(resultSet.next()) {
            Voiture v = Mapper.toDtoVoiture(resultSet);
            output.add(v);
        }

        return output;
    }

    @Override
    public Voiture selectByID(Connection c, Integer id) throws SQLException {

        String requete = "SELECT \"CarID\", \"Model\", \"Price\", \"EngineID\", \"Cylindree\", \"Carburant\", \"Puissance\"" +
                "FROM public.car JOIN public.engine ON \"MoteurID\" = \"EngineID\" WHERE \"CarID\" = ?";
        Voiture v = null;

        PreparedStatement preparedStatement = c.prepareStatement(requete);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            v = Mapper.toDtoVoiture(resultSet);
        }

        return v;
    }

    @Override
    public void insert(Connection c, Voiture v) throws SQLException {

        String requete = "INSERT INTO public.car VALUES (DEFAULT, ?, ?, ?)";
        PreparedStatement preparedStatement = c.prepareStatement(requete);
        preparedStatement.setString(1, v.getModele());
        preparedStatement.setDouble(2, v.getPrice());
        preparedStatement.setInt(3, v.getMoteur().getId());

        preparedStatement.executeUpdate();

    }

    //TODO ajouter la gestion du moteur pour la mise à jour via MoteurID
    @Override
    public void update(Connection c, Voiture v, Integer id) throws SQLException {

        String requete = "UPDATE public.car SET \"Model\" = ?, \"Price\" = ? WHERE \"CarID\" = ?";
        PreparedStatement preparedStatement = c.prepareStatement(requete);
        preparedStatement.setString(1, v.getModele());
        preparedStatement.setDouble(2, v.getPrice());
        preparedStatement.setInt(3, id);

        preparedStatement.executeUpdate();

    }

    @Override
    public void delete(Connection c, Integer id) throws SQLException {

        String requete = "DELETE FROM public.car WHERE \"CarID\" = ?";
        PreparedStatement preparedStatement = c.prepareStatement(requete);
        preparedStatement.setInt(1, id);

        preparedStatement.executeUpdate();
    }
}
