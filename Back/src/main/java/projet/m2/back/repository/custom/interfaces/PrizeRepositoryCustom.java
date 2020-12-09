package projet.m2.back.repository.custom.interfaces;

import org.springframework.data.repository.CrudRepository;
import projet.m2.back.entity.Prize;

public interface PrizeRepositoryCustom   {

    int updatePrize(long id, int quantity);
}
