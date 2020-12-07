package projet.m2.back.entity;

import javax.persistence.*;

@Entity
public class Square {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int index;
    private String streetName;
    private String color;
    private int cost;
    @ManyToOne
    private Board board;


    public Square(long id, String streetName, String color, int cost) {
        this.id = id;
        this.streetName = streetName;
        this.color = color;
        this.cost = cost;
    }

    public Square() {

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
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }
}
