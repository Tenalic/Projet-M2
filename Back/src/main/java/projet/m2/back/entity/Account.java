package projet.m2.back.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Représente un compte
 * Entité représentant un utilisateur avec ses informations personnelles
 * Le password est stocké haché en base de données
 *
 * Attributs:
 * nbDice : nombre de lancers de dés restant à  l'utilisateur
 * credit : argent virtuel dont dispose l'utilisateur pour acheter des cases du plateau
 * indexSquare : position relative actuelle de l'utilisateur sur le plateau de jeu
 */
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String lastname;
    private String firstname;
    @Column(unique = true)
    private String nickname;
    @Column(unique = true)
    private String email;
    @JsonIgnore
    private String password;
    private int nbDice;
    private int credit;
    private int indexSquare;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ElementCollection
    private Collection<Integer> indexSquarePurchased;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ElementCollection
    private Collection<String> prize;

    public Account(long id, String lastname, String firstname, String nickname, String email, String password,
                   int nbDice, int credit, Collection<Integer> indexSquarePurchased, Collection<String> prize) {
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

    public Account(long id, String lastname, String firstname, String nickname, String email, String password,
                   int nbDice, int credit, Collection<Integer> indexSquarePurchased, Collection<String> prize, int indexSquare) {
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
        this.indexSquare = indexSquare;
    }

    public Account() {
    }

    public Account(String lastname, String firstname, String nickname, String email, String password) {
        this.firstname = firstname;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.nbDice = 0;
        this.credit = 0;
        this.lastname = lastname;
        indexSquarePurchased = new ArrayList<>();
        prize = new ArrayList<>();
    }

    public Account(String email) {
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

    public Collection<String> getPrize() {
        return prize;
    }

    public void setPrize(Collection<String> prize) {
        this.prize = prize;
    }

    public int getIndexSquare() {
        return indexSquare;
    }

    public void setIndexSquare(int indexSquare) {
        this.indexSquare = indexSquare;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", nbDice=" + nbDice +
                ", credit=" + credit +
                ", indexSquarePurchased=" + indexSquarePurchased +
                ", prize=" + prize +
                '}';
    }
}
