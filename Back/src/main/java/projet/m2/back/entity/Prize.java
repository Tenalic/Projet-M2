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

    public Prize(long id, String reward) {
        this.id = id;
        this.reward = reward;
    }

    public Prize() {

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
}
