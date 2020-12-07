package projet.m2.back.entity;

import javax.persistence.*;
import java.util.Collection;


@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    private int nbSquare;

    @OneToMany(mappedBy = "board")
    private Collection<Square> board;

    public Board(long id, int nbSquare, Collection<Square> board) {
        this.id = id;
        this.nbSquare = nbSquare;
        this.board = board;
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
}
