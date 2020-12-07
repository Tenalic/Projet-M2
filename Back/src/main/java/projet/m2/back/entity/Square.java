package projet.m2.back.entity;

public class Square {

    long id;
    String name;
    String color;
    int cost;
    boolean bought;



    public Square(long id, String name, String color, int cost, boolean bought) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.cost = cost;
        this.bought = bought;
    }




    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public boolean isBought() {
        return bought;
    }

    public void setBought(boolean bought) {
        this.bought = bought;
    }
}
