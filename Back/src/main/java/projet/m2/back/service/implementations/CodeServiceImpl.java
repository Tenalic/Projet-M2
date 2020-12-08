package projet.m2.back.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.m2.back.entity.Account;
import projet.m2.back.entity.Code;
import projet.m2.back.repository.CodeRepository;
import projet.m2.back.service.interfaces.IAccountService;
import projet.m2.back.service.interfaces.ICodeService;

import java.util.logging.Logger;

@Service
public class CodeServiceImpl implements ICodeService {

    Logger logger = Logger.getLogger("log");

    @Autowired
    CodeRepository repoCode;

    @Autowired
    IAccountService accountService;

    @Override
    public int useCode(final long idAccount, final String code) {
        int backCode;
        long codeLong = Long.parseLong(code);
        Code codeBDD = repoCode.findByCode(codeLong);
        if (!codeBDD.isUsed()) {
            codeBDD.setUsed(true);
            Account account = accountService.getInfo(idAccount);
            if (account != null) {
                //TODO gerer gagner aléatoirement un prix
                account.setNbDice(account.getNbDice() + 1);
                repoCode.updateCode(codeBDD);
                accountService.updateAccount(account);
                backCode = 0;
            } else {
                backCode = 1;
            }
        } else {
            backCode = 2;
        }
        return backCode;
    }

    public int changeUsed(final long id, final boolean used) {
        repoCode.changeUsed(id, used);
        return 0;
    }
}
