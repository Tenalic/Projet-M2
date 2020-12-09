package projet.m2.back.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Prize {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String reward;
    private int quantity;

    public Prize() {
    }

    public Prize(String reward, int quantity){
        this.reward = reward;
        this.quantity = quantity;
    }

    public Prize(long id, String reward) {
        this.id = id;
        this.reward = reward;
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

    @Override
    public String toString() {
        return "Prize{" +
                "id=" + id +
                ", reward='" + reward + '\'' +
                '}';
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
