package projet.m2.back.service.interfaces;

import projet.m2.back.entity.Square;

public interface ISquareService {

    /**
     * Récupérer le cout d'une case en fonction de son index
     *
     * @param indexSquare : l'index de la case
     * @return le cout de l'index
     */
    int getCost(int indexSquare);

    /**
     * Récupérer une case par son index
     *
     * @param indexSquare : l'index de la case
     * @return la case, null sinon
     */
    Square getSquare(int indexSquare);

    /**
     * Créer une case en base de donnée
     *
     * @param square : la case a crée
     * @return La case crée si ok, null sinon
     */
    Square createSquare(Square square);
}
