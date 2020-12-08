package projet.m2.back.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.m2.back.constant.Constant;
import projet.m2.back.entity.Account;
import projet.m2.back.entity.Board;
import projet.m2.back.entity.Square;
import projet.m2.back.repository.BoardRepository;
import projet.m2.back.service.interfaces.IBoardService;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
public class BoardServiceImpl implements IBoardService {

    @Autowired
    BoardRepository boardRepository;

    @Override
    public Board getBoardByName(final String name) {
        return boardRepository.findByName(name);
    }

    @Override
    public Board getBoardById(final long id) {
        return boardRepository.findById(id);
    }

    @Override
    public Account moveOnBoard(Account account, final int move) {
        Board board = getBoardByName(Constant.boardName);
        if (board != null) {
            if (account.getIndexSquare() + move < board.getNbSquare()) {
                account.setIndexSquare(account.getIndexSquare() + move);
            } else {
                account.setIndexSquare(account.getIndexSquare() + move - board.getNbSquare());
            }
            return account;
        }
        return null;
    }

    @Transactional
    @Override
    public Board createBord() {
        ArrayList<String> listeCouleur = new ArrayList<>();
        listeCouleur.add("red");
        listeCouleur.add("yellow");
        listeCouleur.add("green");
        listeCouleur.add("blue");
        listeCouleur.add("purple");
        listeCouleur.add("pink");
        listeCouleur.add("orange");
        listeCouleur.add("brown");
        int j = 0;
        ArrayList<Square> listeSquare = new ArrayList<Square>();
        for (int i = 0; i < 20; i++) {
            if (i == 0 || i == 5 || i == 10 || i == 15) {
                listeSquare.add(new Square(i, "street" + i, "white", 60));
            } else {
                if (j < 8) {
                    listeSquare.add(new Square(i, "street" + i, listeCouleur.get(j), 50));
                    j++;
                } else {
                    j = 0;
                    listeSquare.add(new Square(i, "street" + i, listeCouleur.get(j), 50));
                }
            }
        }
        Board board = new Board(20, listeSquare, Constant.boardName);
        Board result = boardRepository.save(board);
        return result;
    }
}
