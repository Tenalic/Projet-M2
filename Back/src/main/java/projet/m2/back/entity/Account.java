package projet.m2.back.entity;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String lastname;
    private String firstname;
    private String nickname;
    private String email;
    private String password;
    private int nbDice;
    private int credit;

    @ElementCollection
    private Collection<Integer> indexSquarePurchased;

    @OneToMany
    private Collection<Prize> prize;

    public Account(long id, String lastname, String firstname, String nickname, String email, String password,
                   int nbDice, int credit, Collection<Integer> indexSquarePurchased, Collection<Prize> prize) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.nbDice = nbDice;
        this.credit = credit;
        this.indexSquarePurchased = indexSquarePurchased;
        this.prize = prize;
    }

    public Account(){
    }

    public Account(String email){
        this.email = email;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getNbDice() {
        return nbDice;
    }

    public void setNbDice(int nbDice) {
        this.nbDice = nbDice;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public Collection<Integer> getIndexSquarePurchased() {
        return indexSquarePurchased;
    }

    public void setIndexSquarePurchased(Collection<Integer> indexSquarePurchased) {
        this.indexSquarePurchased = indexSquarePurchased;
    }

    public Collection<Prize> getPrize() {
        return prize;
    }

    public void setPrize(Collection<Prize> prize) {
        this.prize = prize;
    }
}
