package projet.m2.back.entity;

import javax.persistence.*;

/**
 * Entité représentant les cases du plateau de jeu de Monopoly
 * Intervenant sous forme de collection dans le compte utilisateur (Account)
 * Permet de tracker les utilisateurs pour leur attribuer leur position actuelle sur le plateau
 * Et de lister les cases achetées par ceux-ci
 *
 * Attributs :
 * squareIndex : numéro de la case relatif au plateau (démarrant en bas à droite et avancée dans le sens anti-horaire)
 * streetName : nom de la rue spécifiée sur la case
 * color : couleur de la case
 * cost : coût en crédits de la case
 */
@Entity
public class Square {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int squareIndex;
    private String streetName;
    private String color;
    private int cost;

    public Square() {

    }

    public Square(int squareIndex, String streetName, String color, int cost) {
        this.squareIndex = squareIndex;
        this.streetName = streetName;
        this.color = color;
        this.cost = cost;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getIndex() {
        return squareIndex;
    }

    public void setIndex(int index) {
        this.squareIndex = index;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

}
