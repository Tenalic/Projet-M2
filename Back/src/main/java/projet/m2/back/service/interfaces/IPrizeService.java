package projet.m2.back.service.interfaces;

import projet.m2.back.entity.Account;
import projet.m2.back.entity.Prize;

public interface IPrizeService {

    void createPrize(String reward, int quantity, int weight);

    Iterable<Prize> getAllPrize();

    /**
     * Fonction qui renvoie un gain selon une probabilité ( 60% juste un dé, 10% un dé + 50 points,
     * 10% un dé + 100 points, 10% un dé + 150 points, 10% un dé et un prix)
     * @return 0 si juste dé, 1 si 50 pts, 2 pour 100 pts, 3 pour 150 pts et 4 pour un prix
     */
    Integer gain();

    /**
     * Fonction qui crée un jeu de données pour les prix dans la base de données
     */
    void createDatasetPrize();

    /**
     * Fonction qui permet de tirer aléatoirement un prix.
     * @param account : compte
     * @return le prix qui à été choisi aléatoirement.
     */
    String randomPrize(Account account);


}
