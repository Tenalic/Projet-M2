package projet.m2.back.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Symbolise le plateau de jeu
 */
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int nbSquare;

    @Column(unique = true)
    private String name;


    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "boardId", referencedColumnName = "id")
    private Collection<Square> board;

    public Board(int nbSquare, Collection<Square> board) {
        this.nbSquare = nbSquare;
        this.board = board;
    }

    public Board(int nbSquare, Collection<Square> board, String name) {
        this.nbSquare = nbSquare;
        this.board = board;
        this.name = name;
    }


    public Board() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNbSquare() {
        return nbSquare;
    }

    public void setNbSquare(int nbSquare) {
        this.nbSquare = nbSquare;
    }

    public Collection<Square> getBoard() {
        return board;
    }

    public void setBoard(Collection<Square> board) {
        this.board = board;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
