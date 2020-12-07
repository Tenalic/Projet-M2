package projet.m2.back.entity;

public class Gift {

    private long id;
    private String reward;

    public Gift(long id, String reward) {
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
}
