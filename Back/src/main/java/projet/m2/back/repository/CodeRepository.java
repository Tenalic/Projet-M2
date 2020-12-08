package projet.m2.back.repository;

import org.springframework.data.repository.CrudRepository;
import projet.m2.back.entity.Code;
import projet.m2.back.repository.custom.CodeRepositoryCustom;

public interface CodeRepository extends CrudRepository<Code, Long>, CodeRepositoryCustom {

    Code findByCode(long code);

    Code findById(long id);

    Code deleteById(long id);

}
