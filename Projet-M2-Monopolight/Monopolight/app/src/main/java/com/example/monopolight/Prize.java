package com.example.monopolight;

public class Prize {
    private String reward;
    private int number;
    private String giftImage;

    public Prize(String reward, int number, String prizeImage) {
        this.reward = reward;
        this.number = number;
        this.giftImage = prizeImage;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getGiftImage() {
        return giftImage;
    }

    public void setGiftImage(String giftImage) {
        this.giftImage = giftImage;
    }

}
