package projet.m2.back.service.interfaces;

import projet.m2.back.entity.Account;
import projet.m2.back.entity.Board;

public interface IBoardService {

    /**
     * Permet de récupérer le board selon son nom en base
     * @param name le nom du board en base
     * @return le board
     */
    Board getBoardByName(final String name);

    /**
     * Permet de récupérer le board selon son id en base
     * @param id id du board en base
     * @return le board
     */
    Board getBoardById(final long id);

    /**
     * Permet de changer l'index de la case sur lequel l'Account est sur la nouvelle case selon le move et le sauvgarder en base.
     * @param account le compte sur lequel le move sera fait
     * @param move le nombre de case à bouger
     * @return le compte avec le nouvelle index de la case
     */
    Account moveOnBoard(Account account, int move);

    /**
     * Fonction qui permet de créer les Squares et le Board associé et de le mettre en Base. (Fonction réservé pour le back)
     * @return le Board créer.
     */
    Board createBord();
}
