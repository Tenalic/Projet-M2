package projet.m2.back.repository;

import org.springframework.data.repository.CrudRepository;
import projet.m2.back.entity.Code;

public interface CodeRepository extends CrudRepository<Code, Long> {

    Iterable<Code> findAll();

    Code findByCode(long code);

    Code findById(long id);

    Iterable<Code> findByValue(int value);

    Iterable<Code> findByValueBefore(int value);

    Iterable<Code> findByValueAfter(int value);

    Iterable<Code> findByValueBetween(int valueMin, int valueMax);

    Code deleteById(long id);

}
