package projet.m2.back.repository.custom;

import projet.m2.back.entity.Account;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class AccountRepositoryImpl implements AccountRepositoryCustom {

    @PersistenceContext
    EntityManager em;

    @Override
    public int changeNbDice(long id, int nbDice) {
        Account account = em.find(Account.class, id);
        account.setNbDice(nbDice);
        em.merge(account);
        return 0;
    }

    @Override
    public int updateAccount(Account account) {
        em.merge(account);
        return 0;
    }
}
