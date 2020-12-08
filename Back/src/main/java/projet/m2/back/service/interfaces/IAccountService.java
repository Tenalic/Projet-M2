package projet.m2.back.service.interfaces;

import projet.m2.back.entity.Account;
import projet.m2.back.entity.Prize;

public interface IAccountService {
    Account connection(final String email, final String password);

    Account creationAccount(final String email, final String lastname, final String firstname, final String nickname, final String password);

    Account getInfo(final long id);

    int changeNbDice(final long id, final int nbDice);

    int updateAccount(Account account);

    void deleteAccount(long id);

    boolean accountExistsById(long id);

    /**
     * @param id : id du compte
     * @return : -1 : Compte non trouvé, -2 : nombre de dé insuffisant, -3 : board non trouvé
     */
    int throwDice(final long id);

    boolean buySquare(long id_account);

    Prize checkSquareColorWinner(long idAccount);
}
