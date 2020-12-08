package projet.m2.back.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import projet.m2.back.entity.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
    public Account findAccountByEmail(String email);
    @Query("select a.password From Account a")
    public String findPasswordAccountByEmail(String email);
    public boolean existsAccountByEmailOrNickname(String email, String nickname);
}
