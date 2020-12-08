package projet.m2.back.service.implementations;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.m2.back.entity.Account;
import projet.m2.back.entity.Prize;
import projet.m2.back.entity.Square;
import projet.m2.back.repository.AccountRepository;
import projet.m2.back.service.interfaces.IAccountService;
import projet.m2.back.service.interfaces.IBoardService;
import projet.m2.back.service.interfaces.ISquareService;
import projet.m2.back.service.interfaces.IUtils;

import javax.transaction.Transactional;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private IUtils utils;

    @Autowired
    private IBoardService boardService;

    @Autowired
    private ISquareService squareService;

    @Override
    public Account connection(String email, String password) {
        Account account = accountRepository.findAccountByEmail(email);
        if (account != null) {
            BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), account.getPassword());
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
            return a;
        }
        return null;
    }


    public Account getInfo(long id) {
        return accountRepository.findAccountById(id);
    }

    public int changeNbDice(long id, int nbDice) {
        return accountRepository.changeNbDice(id, nbDice);
    }

    @Override
    @Transactional
    public int updateAccount(final Account account) {
        accountRepository.updateAccount(account);
        return 0;
    }

    @Transactional
    public void deleteAccount(long id) {
        accountRepository.deleteAccountById(id);
    }

    public boolean accountExistsById(long id) {
        return accountRepository.existsAccountById(id);
    }

    @Override
    public int throwDice(long id) {
        int backCode;
        Account account = accountRepository.findAccountById(id);
        if (account != null) {
            if (account.getNbDice() >= 2) {
                account.setNbDice(account.getNbDice() - 2);
                account = boardService.moveOnBoard(account, utils.rand(1, 6));
                if(account != null) {
                    backCode = account.getIndexSquare();
                } else {
                    backCode = -3;
                }
            } else {
                backCode = -2;
            }
        } else {
            backCode = -1;
        }
        return backCode;
    }

    @Override
    @Transactional
    public boolean buySquare(long idAccount){
        Account a = accountRepository.findAccountById(idAccount);
        int indexSquareBase = a.getIndexSquare();
        if(!a.getIndexSquarePurchased().contains(indexSquareBase) &&
                (indexSquareBase != 0 &&
                        indexSquareBase != 5 &&
                        indexSquareBase != 10 &&
                        indexSquareBase != 15)){
            int costSquare = squareService.getCost(indexSquareBase);
            if(a.getCredit() >= costSquare){
                a.getIndexSquarePurchased().add(indexSquareBase);
                a.setCredit(a.getCredit() - costSquare);
                accountRepository.updateAccount(a);
                return true;
            }
        }
        return false;
    }

    @Override
    @Transactional
    public Prize checkSquareColorWinner(long idAccount) {
        Account a = accountRepository.findAccountById(idAccount);
        Integer[] listSquare = (Integer[])a.getIndexSquarePurchased().toArray();
        for (int i = 0; i < listSquare.length - 1 ; i++) {
            Square square1 = squareService.getSquare(listSquare[i]);
            for(int j = i+1; j < listSquare.length; j++){
                Square square2 = squareService.getSquare(listSquare[j]);
                if(square1.getColor().equals(square2.getColor())){
                    a.getIndexSquarePurchased().remove(square1.getIndex());
                    a.getIndexSquarePurchased().remove(square2.getIndex());
                    //TODO CHOISIR LE PRIZE A RETOURNER ET MODIF LA BASE
                    accountRepository.updateAccount(a);
                    return null;
                }
            }
        }

        return null;
    }


}
