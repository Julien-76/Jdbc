package be.technifutur.service;

import be.technifutur.dto.Moteur;
import be.technifutur.mapper.Mapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MoteurService implements Crudable<Moteur, Integer> {

    @Override
    public List<Moteur> selectAll(Connection c) throws SQLException {

        List<Moteur> output = new ArrayList<>();

        Statement statement = c.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM public.engine");
        while(resultSet.next()) {
            Moteur m = Mapper.toDtoMoteur(resultSet);
            output.add(m);
        }

        return output;
    }

    @Override
    public Moteur selectByID(Connection c, Integer id) throws SQLException {

        String requete = "SELECT * FROM public.engine WHERE \"EngineID\" = ?";
        Moteur m = null;

        PreparedStatement preparedStatement = c.prepareStatement(requete);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            m = Mapper.toDtoMoteur(resultSet);
        }

        return m;
    }

    @Override
    public void insert(Connection c, Moteur v) throws SQLException {

        String requete = "INSERT INTO public.engine VALUES (DEFAULT, ?, CAST(? AS carburant), ?)";
        PreparedStatement preparedStatement = c.prepareStatement(requete);
        preparedStatement.setInt(1, v.getCylindree());
        preparedStatement.setString(2, v.getCarburant().name());
        preparedStatement.setInt(3, v.getPuissance());

        preparedStatement.executeUpdate();

    }

    @Override
    public void update(Connection c, Moteur v, Integer id) throws SQLException {

        String requete = "UPDATE public.engine SET \"Cylindree\" = ?, \"Carburant\" = CAST(? AS carburant), \"Puissance\" = ? WHERE \"EngineID\" = ?";
        PreparedStatement preparedStatement = c.prepareStatement(requete);
        preparedStatement.setInt(1, v.getCylindree());
        preparedStatement.setString(2, v.getCarburant().name());
        preparedStatement.setInt(3, v.getPuissance());
        preparedStatement.setInt(4, id);

        preparedStatement.executeUpdate();


    }

    @Override
    public void delete(Connection c, Integer id) throws SQLException {

        String requete = "DELETE FROM public.engine WHERE \"EngineID\" = ?";
        PreparedStatement preparedStatement = c.prepareStatement(requete);
        preparedStatement.setInt(1, id);

        preparedStatement.executeUpdate();
    }
}
