package projet.m2.back.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import projet.m2.back.entity.Account;
import projet.m2.back.repository.custom.interfaces.AccountRepositoryCustom;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long>, AccountRepositoryCustom {
    Account findAccountByEmail(String email);

    Account findAccountById(long id);

    boolean existsAccountByEmailOrNickname(String email, String nickname);

    void deleteAccountById(long id);

    boolean existsAccountById(long id);

    boolean existsAccountByNickname(String nickname);

}
