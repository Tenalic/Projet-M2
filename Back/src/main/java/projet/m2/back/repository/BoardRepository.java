package projet.m2.back.repository;

import org.springframework.data.repository.CrudRepository;
import projet.m2.back.entity.Board;

public interface BoardRepository extends CrudRepository<Board, Long> {

    Board findById(long id);

    Board findByName(String name);
}
