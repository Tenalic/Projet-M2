package projet.m2.back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.m2.back.constant.Constant;
import projet.m2.back.entity.Account;
import projet.m2.back.entity.Board;
import projet.m2.back.repository.BoardRepository;

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
}
