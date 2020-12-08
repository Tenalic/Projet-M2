package projet.m2.back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.m2.back.entity.Account;
import projet.m2.back.repository.AccountRepository;

@Service
public class AccountServiceImpl implements IaccountService{

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account connexion(String email, String password) {
        Account account = accountRepository.findAccountByEmail(email);
        if(account != null){

        }
        return null;
    }

    @Override
    public Account creationAccount(String email, String lastname, String firstname, String nickname, String password) {
        return null;
    }

    @Override
    public Account getAccountById(long id) {
        return null;
        //return accountRepository.findById(id);
    }


}
