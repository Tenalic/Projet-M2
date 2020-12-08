package projet.m2.back.repository.custom;

import projet.m2.back.entity.Account;

public interface AccountRepositoryCustom {

    int changeNbDice(long id, int nbDice);

    int updateAccount(Account account);
}
