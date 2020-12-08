package projet.m2.back.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import projet.m2.back.entity.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
    public Account findAccountByEmail(String email);
    public Account findAccountById(long id);
}
