package projet.m2.back.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import projet.m2.back.entity.Prize;
import projet.m2.back.repository.custom.interfaces.PrizeRepositoryCustom;

import java.util.List;

public interface PrizeRepository extends CrudRepository<Prize, Long>, PrizeRepositoryCustom {

    Prize findById(long id);


}
