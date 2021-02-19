package be.technifutur.dto;

import java.util.Objects;

public class Voiture {

    private int id;

    private String modele;

    private Double price;

    private Moteur moteur;

    public Voiture(int id, String modele, Double price, Moteur moteur) {
        this.id = id;
        this.modele = modele;
        this.price = price;
        this.moteur = moteur;
    }

    public Voiture(String modele, Double price, Moteur moteur) {
        this.modele = modele;
        this.price = price;
        this.moteur = moteur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Moteur getMoteur() {
        return moteur;
    }

    public void setMoteur(Moteur moteur) {
        this.moteur = moteur;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Voiture)) return false;
        Voiture voiture = (Voiture) o;
        return id == voiture.id &&
                modele.equals(voiture.modele) &&
                price.equals(voiture.price) &&
                moteur.equals(voiture.moteur);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, modele, price, moteur);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Voiture{");
        sb.append("id=").append(id);
        sb.append(", modele='").append(modele).append('\'');
        sb.append(", price=").append(price);
        sb.append(", moteur=").append(moteur);
        sb.append('}');
        return sb.toString();
    }
}
