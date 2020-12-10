package projet.m2.back.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.m2.back.entity.Square;
import projet.m2.back.repository.SquareRepository;
import projet.m2.back.service.interfaces.ISquareService;

import javax.transaction.Transactional;

@Service
public class SquareServiceImpl implements ISquareService {
    @Autowired
    private SquareRepository squareRepository;

    /**
     * Récupérer le cout d'une case en fonction de son index
     *
     * @param indexSquare : l'index de la case
     * @return le cout de l'index
     */
    @Override
    public int getCost(int indexSquare) {
        return squareRepository.findCostSquareByIndex(indexSquare);
    }

    /**
     * Recuperer une case par son index
     *
     * @param indexSquare : l'index de la case
     * @return la case, null sinon
     */
    public Square getSquare(int indexSquare) {
        return squareRepository.findSquareBySquareIndex(indexSquare);
    }

    /**
     * Creer une case en base de donnée
     *
     * @param square : la case a crée
     * @return La case crée si ok, null sinon
     */
    @Transactional
    @Override
    public Square createSquare(Square square) {
        return squareRepository.save(square);
    }
}
