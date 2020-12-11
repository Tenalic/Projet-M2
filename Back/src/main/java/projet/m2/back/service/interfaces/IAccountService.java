package projet.m2.back.service.interfaces;

import projet.m2.back.entity.Account;
import projet.m2.back.entity.Prize;

import java.util.Map;

public interface IAccountService {

    /**
     * Renvoie un compte si les identifiant renseigné en entrée sont présents en base de données.
     *
     * @param email    : email du compte
     * @param password : mot de passe du compte
     * @return Compte si identifiant et mot de passe correcte, null sinon
     */
    Account connection(final String email, final String password);

    /**
     * Crée un compte en base de donnée, renvoie une erreur si l'email ou le surnom existe déjà
     *
     * @param email     : email
     * @param lastname  : nom
     * @param firstname : prénom
     * @param nickname  : pseudo
     * @param password  : mot de passe
     * @return Compte qui viens d'être crée ou 1 : email déja utilisé, 2 pseudo déja utilisé
     */
    Object creationAccount(final String email, final String lastname, final String firstname, final String nickname, final String password);

    /**
     * Retourne un compte
     *
     * @param id : id du compte
     * @return Account
     */
    Account getInfo(final long id);

    /**
     * Augmente le nombre de  dé du compte d'id donnée en param
     *
     * @param id     : id du compte
     * @param nbDice : la valeur dont le nombre de dé serra augmenter
     * @return 0
     */
    int changeNbDice(final long id, final int nbDice);

    /**
     * Met a jour le compte donnée en paramètre
     *
     * @param account : compte
     * @return 0
     */
    int updateAccount(Account account);

    /**
     * Modifie les attribut donnée en entrée
     *
     * @param listModifyValue : liste des attribut a modifier
     * @param id              : id du compte
     * @return Account, null si compte non trouvé
     */
    Account modifyValue(Map<String, String> listModifyValue, long id);

    /**
     * Supprime le compte
     *
     * @param id : id du compte
     */
    void deleteAccount(long id);

    /**
     * Savoir si un compte existe
     *
     * @param id : id du compte
     * @return true ou false
     */
    boolean accountExistsById(long id);

    /**
     * Savoir si un compte existe
     *
     * @param nickname : pseudo du compte
     * @return true ou false
     */
    boolean accountExistsByNickname(String nickname);


    /**
     * @param id : id du compte
     * @return : -1 : Compte non trouvé, -2 : nombre de dé insuffisant, -3 : board non trouvé
     */
    Object throwDice(final long id);

    /**
     * Achete la case sur la quelle l'utilisateur se trouve
     *
     * @param id_account : id du compte
     * @return vrai si acheté, faux sinon
     */
    boolean buySquare(long id_account);

    /**
     * Verifie si l'utilisateur n'a pas 2 case de la même couleur dans sa liste de case acheté, si c'est le cas, les supprime de sa liste de case et lui fait gagné un prix
     *
     * @param idAccount : id du compte
     * @return la liste des prix gagnée ou null
     */
    String checkSquareColorWinner(long idAccount);

    /**
     * Permet de récupérer le nickname avec l'id du compte
     * @param idAccount l'id du compte
     * @return le nickname, si le compte existe, null sinon
     */
    String getNicknameById(long idAccount);
}
