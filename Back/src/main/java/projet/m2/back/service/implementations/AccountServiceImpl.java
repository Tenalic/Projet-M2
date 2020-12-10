package projet.m2.back.service.implementations;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.m2.back.entity.Account;
import projet.m2.back.entity.Prize;
import projet.m2.back.entity.Square;
import projet.m2.back.repository.AccountRepository;
import projet.m2.back.service.interfaces.IAccountService;
import projet.m2.back.service.interfaces.IBoardService;
import projet.m2.back.service.interfaces.ISquareService;
import projet.m2.back.service.interfaces.IUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private IUtils utils;

    @Autowired
    private IBoardService boardService;

    @Autowired
    private ISquareService squareService;

    @Autowired
    private PrizeServiceImpl prizeService;

    /**
     * Renvoie un compte si les identifiant renseigné en entrée sont présents en base de données.
     *
     * @param email    : email du compte
     * @param password : mot de passe du compte
     * @return Compte si identifiant et mot de passe correcte, null sinon
     */
    @Override
    public Account connection(String email, String password) {
        Account account = accountRepository.findAccountByEmail(email);
        if (account != null) {
            BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), account.getPassword());
            if (result.verified) {
                return account;
            }
        }
        return null;
    }

    /**
     * Crée un compte en base de donnée, renvoie une erreur si l'email ou le surnom existe déjà
     *
     * @param email     : email
     * @param lastname  : nom
     * @param firstname : prénom
     * @param nickname  : pseudo
     * @param password  : mot de passe
     * @return Compte qui viens d'être crée
     */
    @Override
    @Transactional
    public Account creationAccount(String email, String lastname, String firstname, String nickname, String password) {
        if (!accountRepository.existsAccountByEmailOrNickname(email, nickname)) {
            String hashedPassword = BCrypt.withDefaults().hashToString(12, password.toCharArray());
            Account a = new Account(lastname, firstname, nickname, email, hashedPassword);
            accountRepository.save(a);
            return a;
        }
        return null;
    }

    /**
     * Retourne un compte
     *
     * @param id : id du compte
     * @return Account
     */
    public Account getInfo(long id) {
        return accountRepository.findAccountById(id);
    }

    /**
     * Augmente le nombre de  dé du compte d'id donnée en param
     *
     * @param id     : id du compte
     * @param nbDice : la valeur dont le nombre de dé serra augmenter
     * @return 0
     */
    public int changeNbDice(long id, int nbDice) {
        return accountRepository.changeNbDice(id, nbDice);
    }

    /**
     * Met a jour le compte donnée en paramètre
     *
     * @param account : compte
     * @return 0
     */
    @Override
    @Transactional
    public int updateAccount(final Account account) {
        accountRepository.updateAccount(account);
        return 0;
    }

    /**
     * Modifie les attribut donnée en entrée
     *
     * @param listModifyValue : liste des attribut a modifier
     * @param id              : id du compte
     * @return Account, null si compte non trouvé
     */
    @Transactional
    @Override
    public Account modifyValue(Map<String, String> listModifyValue, long id) {
        Set<String> keylist = listModifyValue.keySet();
        Account account = accountRepository.findAccountById(id);
        if (account != null) {
            for (String key : keylist) {
                switch (key) {
                    case "lastname":
                        account.setLastname(listModifyValue.get(key));
                        break;
                    case "firstname":
                        account.setFirstname(listModifyValue.get(key));
                        break;
                    case "nickname":
                        account.setNickname(listModifyValue.get(key));
                        break;
                }

            }
            accountRepository.updateAccount(account);
        }
        return account;
    }

    /**
     * Supprime le compte
     *
     * @param id : id du compte
     */
    @Transactional
    public void deleteAccount(long id) {
        accountRepository.deleteAccountById(id);
    }

    /**
     * Savoir si un compte existe
     *
     * @param id : id du compte
     * @return true ou false
     */
    public boolean accountExistsById(long id) {
        return accountRepository.existsAccountById(id);
    }

    /**
     * Savoir si un compte existe
     *
     * @param nickname : pseudo du compte
     * @return true ou false
     */
    @Override
    public boolean accountExistsByNickname(String nickname) {
        return accountRepository.existsAccountByNickname(nickname);
    }

    /**
     * Lance 2 dé et fait avancer l'utilisateur du nombre de case
     * /**
     *
     * @param id : id du compte
     * @return : -1 : Compte non trouvé, -2 : nombre de dé insuffisant, -3 : board non trouvé
     */
    @Override
    @Transactional
    public Object throwDice(long id) {
        Integer backCode;
        Account account = accountRepository.findAccountById(id);
        if (account != null) {
            if (account.getNbDice() >= 2) {
                account.setNbDice(account.getNbDice() - 2);
                int diceResult[] = {utils.rand(1, 6), utils.rand(1, 6)};
                account = boardService.moveOnBoard(account, diceResult[0] + diceResult[1]);
                if (account != null) {
                    backCode = account.getIndexSquare();
                    accountRepository.updateAccount(account);
                    Object[] returnFunc = {account, diceResult};
                    return returnFunc;
                } else {
                    backCode = -3;
                }
            } else {
                backCode = -2;
            }
        } else {
            backCode = -1;
        }
        return backCode;
    }

    /**
     * Achete la case sur la quelle l'utilisateur se trouve
     *
     * @param idAccount : id du compte
     * @return vrai si acheté, faux sinon
     */
    @Override
    @Transactional
    public boolean buySquare(long idAccount) {
        Account a = accountRepository.findAccountById(idAccount);
        int indexSquareBase = a.getIndexSquare();
        if (!a.getIndexSquarePurchased().contains(indexSquareBase) &&
                (indexSquareBase != 0 &&
                        indexSquareBase != 5 &&
                        indexSquareBase != 10 &&
                        indexSquareBase != 15)) {
            int costSquare = squareService.getCost(indexSquareBase);
            if (a.getCredit() >= costSquare) {
                a.getIndexSquarePurchased().add(indexSquareBase);
                a.setCredit(a.getCredit() - costSquare);
                accountRepository.updateAccount(a);
                return true;
            }
        }
        return false;
    }

    /**
     * Verifie si l'utilisateur n'a pas 2 case de la même couleur dans sa liste de case acheté, si c'est le cas, les supprime de sa liste de case et lui fait gagné un prix
     *
     * @param idAccount : id du compte
     * @return la liste des prix gagnée ou null
     */
    @Override
    @Transactional
    public String checkSquareColorWinner(long idAccount) {
        Account a = accountRepository.findAccountById(idAccount);
        List<Integer> listSquare = (List<Integer>) a.getIndexSquarePurchased();
        for (int i = 0; i < listSquare.size() - 1; i++) {
            Square square1 = squareService.getSquare(listSquare.get(i));
            for (int j = i + 1; j < listSquare.size(); j++) {
                Square square2 = squareService.getSquare(listSquare.get(j));
                if (square1.getColor().equals(square2.getColor())) {
                    a.getIndexSquarePurchased().remove(square1.getIndex());
                    a.getIndexSquarePurchased().remove(square2.getIndex());
                    String prize = prizeService.randomPrize(a);
                    accountRepository.updateAccount(a);
                    return prize;
                }
            }
        }

        return null;
    }


}
