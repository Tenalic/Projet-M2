package projet.m2.back.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.m2.back.entity.Account;
import projet.m2.back.entity.Code;
import projet.m2.back.repository.CodeRepository;
import projet.m2.back.service.interfaces.IAccountService;
import projet.m2.back.service.interfaces.ICodeService;
import projet.m2.back.service.interfaces.IPrizeService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

@Service
public class CodeServiceImpl implements ICodeService {

    Logger logger = Logger.getLogger("log");

    @Autowired
    CodeRepository repoCode;

    @Autowired
    IAccountService accountService;

    @Autowired
    IPrizeService prizeService;

    @Transactional
    @Override
    public Object useCode(final long idAccount, final String code) {
        Integer backCode;
        long codeLong = Long.parseLong(code);
        String prize = null;
        Code codeBDD = repoCode.findByCode(codeLong);
        if (codeBDD != null) {
            if (!codeBDD.isUsed()) {
                codeBDD.setUsed(true);
                Account account = accountService.getInfo(idAccount);
                if (account != null) {
                    int result = prizeService.gain();
                    switch (result) {
                        case 0:
                            account.setNbDice(account.getNbDice() + 1);
                            break;
                        case 1:
                            account.setNbDice(account.getNbDice() + 1);
                            account.setCredit(account.getCredit() + 50);
                            break;
                        case 2:
                            account.setNbDice(account.getNbDice() + 1);
                            account.setCredit(account.getCredit() + 100);
                            break;
                        case 3:
                            account.setNbDice(account.getNbDice() + 1);
                            account.setCredit(account.getCredit() + 150);
                            break;
                        case 4:
                            prize = prizeService.randomPrize(account);
                            break;
                        default:
                            account.setNbDice(account.getNbDice() + 1);
                            break;
                    }
                    repoCode.updateCode(codeBDD);
                    accountService.updateAccount(account);
                    return account;
                } else {
                    backCode = 1;
                }
            } else {
                backCode = 2;
            }
        } else {
            backCode = 3;
        }
        return backCode;
    }

    public int changeUsed(final long id, final boolean used) {
        repoCode.changeUsed(id, used);
        return 0;
    }

    public void createDatasetCode() {
        Random r = new Random();
        List<Long> codeAlreadyCreated = new ArrayList<>();
        long code;
        for (int i = 0; i < 200; i++) {
            do {
                code = r.nextInt((9999999 - 100000) + 1) + 100000;
            } while (codeAlreadyCreated.contains(code));
            codeAlreadyCreated.add(code);
            Code c = new Code(code, false);
            repoCode.save(c);
        }
    }
}
