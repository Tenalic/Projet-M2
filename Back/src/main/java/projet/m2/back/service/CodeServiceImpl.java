package projet.m2.back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.m2.back.entity.Account;
import projet.m2.back.entity.Code;
import projet.m2.back.repository.CodeRepository;

@Service
public class CodeServiceImpl implements IcodeService {

    @Autowired
    CodeRepository repoCode;

    @Autowired
    IaccountService accountService;

    /**
     * @param idAccount : id du compte ou l'on souhaite appliqué le code
     * @param code : code du code
     * @return 0 : ok, 1 : compte non trouvé, 2 : code deja utilisé, 3 : erreur lors du parse
     */
    @Override
    public int useCode(final String idAccount, final String code) {
        int codeRetour;
        try {
            long codeLong = Long.parseLong(code);
            Code codeBDD = repoCode.findByCode(codeLong);
            if (!codeBDD.isUsed()) {
                codeBDD.setUsed(true);
                long idAccountLong = Long.parseLong(idAccount);
                Account account = accountService.getInfo(idAccountLong);
                if (account != null) {
                    //TODO gerer gagner aléatoirement un prix
                    account.setNbDice(account.getNbDice() + 1);
                    repoCode.updateCode(codeBDD);
                    accountService.updateAccount(account);
                    codeRetour = 0;
                } else {
                    codeRetour = 1;
                }
            } else {
                codeRetour = 2;
            }
        } catch (NumberFormatException e) {
            System.out.println(e);
            codeRetour = 3;
        }
        return codeRetour;
    }

    public int changeUsed(final long id, final boolean used) {
        repoCode.changeUsed(id, used);
        return 0;
    }
}
