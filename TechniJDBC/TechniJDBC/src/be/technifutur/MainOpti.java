package be.technifutur;

import be.technifutur.dto.Carburant;
import be.technifutur.dto.Moteur;
import be.technifutur.dto.Voiture;
import be.technifutur.service.MoteurService;
import be.technifutur.service.VoitureService;
import be.technifutur.util.ConnexionPSQL;

import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MainOpti {

    public static void main(String[] args) {

        VoitureService voitureService = new VoitureService();

        MoteurService moteurService = new MoteurService();

        try {
            List<Moteur> moteurs = moteurService.selectAll(ConnexionPSQL.getInstance());

//            Voiture uneVoiture = voitureService.selectByID(ConnexionPSQL.getInstance(), 2);
//            afficher(uneVoiture);
//             voitureService.insert(ConnexionPSQL.getInstance(), new Voiture("Mazda MX5", 29990D, moteurs.get(0)));
//
//            List<Voiture> voitures = voitureService.selectAll(ConnexionPSQL.getInstance());
//            afficher(voitures);

            // service.delete(ConnexionPSQL.getInstance(), 3);
            // voitureService.update(ConnexionPSQL.getInstance(), new Voiture("Fiat Punto", 14587D), 13);

//            List<Voiture> voitures = voitureService.selectAll(ConnexionPSQL.getInstance());
//            afficher(voitures);


            afficher(moteurs);

            // moteurService.insert(ConnexionPSQL.getInstance(), new Moteur(2995, Carburant.Essence, 250));

        } catch (SQLException e) {
            System.err.println("Message : "  +e.getMessage());
            System.err.println("Code erreur : " +e.getErrorCode());
            System.err.println("Etat SQL : " +e.getSQLState());
        }

    }

    private static <T> void afficher(List<T> items) {

        if (items != null && !items.isEmpty()) {
            switch (items.get(0).getClass().getSimpleName()) {
                case "Voiture":
                    items.stream()
                            .sorted(Comparator.comparingInt(value -> ((Voiture) value).getId()))
                            .forEach(System.out::println);
                    break;
                case "Moteur":
                    items.stream()
                            .sorted(Comparator.comparingInt(value -> ((Moteur) value).getId()))
                            .forEach(System.out::println);
            }
        }

    }

    private static <T> void afficher(T item) {

        if (item != null) {
            System.out.println(item.toString());
        }

    }

}





