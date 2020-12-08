package projet.m2.back.service.interfaces;

import projet.m2.back.entity.Square;

public interface ISquareService {
    int getCost(int indexSquare);
    Square getSquare(int indexSquare);
}
