package projet.m2.back.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.m2.back.entity.Account;
import projet.m2.back.repository.AccountRepository;

import javax.transaction.Transactional;

@Service
public class AccountServiceImpl implements IaccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account connexion(String email, String password) {
        Account account = accountRepository.findAccountByEmail(email);
        if (account != null) {
            BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), password);
            if (result.verified) {
                return account;
            }
        }
        return null;
    }

    @Override
    @Transactional
    public Account creationAccount(String email, String lastname, String firstname, String nickname, String password) {
        if (!accountRepository.existsAccountByEmailOrNickname(email, nickname)) {
            String hashedPassword = BCrypt.withDefaults().hashToString(12, password.toCharArray());
            Account a = new Account(lastname, firstname, nickname, email, hashedPassword);
            accountRepository.save(a);
        }
        return null;
    }


    public Account getInfo(long id) {
        return accountRepository.findAccountById(id);
    }
}
