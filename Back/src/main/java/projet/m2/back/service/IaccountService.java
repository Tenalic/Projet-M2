package projet.m2.back.service;

import projet.m2.back.entity.Account;

public interface IaccountService {
    public Account connexion(String email, String password);
    public Account creationAccount(String email, String lastname, String firstname, String nickname, String password);
}
