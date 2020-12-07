package projet.m2.back.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

    private Collection<Square> board;
    private Collection<Gift> gift;

    public Account(long id, String lastname, String firstname, String nickname, String email, String password,
                   int nbDice, int credit, Collection<Square> board, Collection<Gift> gift) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.nbDice = nbDice;
        this.credit = credit;
        this.board = board;
        this.gift = gift;
    }

    public Account(){
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

    public Collection<Square> getBoard() {
        return board;
    }

    public void setBoard(Collection<Square> board) {
        this.board = board;
    }

    public Collection<Gift> getGift() {
        return gift;
    }

    public void setGift(Collection<Gift> gift) {
        this.gift = gift;
    }
}
