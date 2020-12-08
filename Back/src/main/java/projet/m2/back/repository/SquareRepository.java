package projet.m2.back.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import projet.m2.back.entity.Square;

public interface SquareRepository extends CrudRepository<Square, Long> {
    @Query("select s.cost from Square s Where s.squareIndex = ?1")
    int findCostSquareByIndex(int index);

    Square findSquareBySquareIndex(int squareIndex);
}
