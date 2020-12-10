package projet.m2.back.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Classe Prize qui contient les paramètres d"un prix stocké dans la Base de Donnée.
 * String Reward contient le nom du prix.
 * int quantity contient le nombre d'exemplaire du prix disponible dans la base de donnée.
 * int probWeight contient un poid pour la génération aléatoire du prix.
 */

@Entity
public class Prize {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String reward;
    private int quantity;
    private int probWeight;

    public Prize() {
    }

    public Prize(String reward, int quantity, int probWeight){
        this.reward = reward;
        this.quantity = quantity;
        this.probWeight = probWeight;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getProbWeight() {
        return probWeight;
    }

    public void setProbWeight(int probWeight) {
        this.probWeight = probWeight;
    }
}
