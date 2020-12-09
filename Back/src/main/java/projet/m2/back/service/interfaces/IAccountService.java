package projet.m2.back.service.interfaces;

import projet.m2.back.entity.Account;
import projet.m2.back.entity.Prize;

import java.util.Map;

public interface IAccountService {
    Account connection(final String email, final String password);

    Account creationAccount(final String email, final String lastname, final String firstname, final String nickname, final String password);

    Account getInfo(final long id);

    int changeNbDice(final long id, final int nbDice);

    int updateAccount(Account account);

    Account modifyValue(Map<String,String> listModifyValue, long id);

    void deleteAccount(long id);

    boolean accountExistsById(long id);

    boolean accountExistsByNickname (String nickname);



    /**
     * @param id : id du compte
     * @return : -1 : Compte non trouvé, -2 : nombre de dé insuffisant, -3 : board non trouvé
     */
    Object throwDice(final long id);

    boolean buySquare(long id_account);

    String checkSquareColorWinner(long idAccount);
}
