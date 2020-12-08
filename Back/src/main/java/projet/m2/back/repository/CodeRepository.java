package projet.m2.back.repository;

import org.springframework.data.repository.CrudRepository;
import projet.m2.back.entity.Code;

public interface CodeRepository extends CrudRepository<Code, Long> {

    Code findByCode(long code);



}
