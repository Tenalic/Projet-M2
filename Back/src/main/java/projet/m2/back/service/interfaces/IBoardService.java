package projet.m2.back.service.interfaces;

import projet.m2.back.entity.Account;
import projet.m2.back.entity.Board;

public interface IBoardService {

    Board getBoardByName(final String name);

    Board getBoardById(final long id);

    Account moveOnBoard(Account account, int move);

    Board createBord();
}
