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

    @Override
    public int getCost(int indexSquare) {
        return squareRepository.findCostSquareByIndex(indexSquare);
    }

    public Square getSquare(int indexSquare) {return squareRepository.findSquareBySquareIndex(indexSquare);}

    @Transactional
    @Override
    public Square createSquare(Square square) {
        return squareRepository.save(square);
    }
}
