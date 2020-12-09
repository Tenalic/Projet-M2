package projet.m2.back.repository.custom.implementations;

import projet.m2.back.entity.Prize;
import projet.m2.back.repository.custom.interfaces.PrizeRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class PrizeRepositoryImpl implements PrizeRepositoryCustom {

    @PersistenceContext
    EntityManager em;

    @Override
    public int updatePrize(long id, int quantity)
    {
        Prize prize = em.find(Prize.class, id);
        prize.setQuantity(quantity);
        em.merge(prize);
        return 0;
    }
}
