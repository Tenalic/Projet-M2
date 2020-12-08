package projet.m2.back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.m2.back.entity.Account;
import projet.m2.back.entity.Code;
import projet.m2.back.repository.CodeRepository;

import java.text.ParseException;

@Service
public class CodeServiceImpl implements IcodeService {

    @Autowired
    CodeRepository repoCode;

    @Autowired
    IaccountService accountService;

    /**
     * @param idAccount
     * @param code
     * @return 0 : ok, 1 : , 2 : code deja utilisé, 3 : erreur lors du parse, 4 : compte non trouvé
     */
    @Override
    public int useCode(String idAccount, String code) {
        int codeRetour;
        try {
            long codeLong = Long.parseLong(code);
            Code codeBDD = repoCode.findByCode(codeLong);
            if (!codeBDD.isUsed()) {
                codeBDD.setUsed(true);
                //gerer gagner aléatoirement un prix
                long idAccountLong = Long.parseLong(idAccount);
                Account account = accountService.getInfo(idAccountLong);
                if (account != null) {
                    account.setNbDice(account.getNbDice() + 1);
                    codeRetour = 0;
                } else {
                    codeRetour = 4;
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
}
