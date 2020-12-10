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

    /**
     * Utilise un code et ajoute les gains au compte
     *
     * @param idAccount : id du compte ou l'on souhaite appliqué le code
     * @param code      : code du code
     * @return 0 : ok, 1 : compte non trouvé, 2 : code deja utilisé, 3 : code inconue, 4 : le code saisie n'est pas un numérique
     */
    @Transactional
    @Override
    public Object useCode(final long idAccount, final String code) {
        Integer backCode = 4;
        try {
            long codeLong = Long.parseLong(code);
            String prize = null;
            int creditWin = 0;
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
                                creditWin = 50;
                                account.setCredit(account.getCredit() + creditWin);
                                break;
                            case 2:
                                creditWin = 100;
                                account.setNbDice(account.getNbDice() + 1);
                                account.setCredit(account.getCredit() + creditWin);
                                break;
                            case 3:
                                creditWin = 150;
                                account.setNbDice(account.getNbDice() + 1);
                                account.setCredit(account.getCredit() + creditWin);
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
                        Object[] tabObjet = {account, prize, creditWin};
                        return tabObjet;
                    } else {
                        backCode = 1;
                    }
                } else {
                    backCode = 2;
                }
            } else {
                backCode = 3;
            }
        } catch (NumberFormatException e) {
            backCode = 4;
        }
        return backCode;
    }

    /**
     * Change si le code est utilisé ou non en base
     *
     * @param id   : id du code
     * @param used : si il est utilisé ou non
     * @return 0
     */
    public int changeUsed(final long id, final boolean used) {
        repoCode.changeUsed(id, used);
        return 0;
    }

    /**
     * Ajoute en base 200 codes générés aléatoirement
     */
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

    /**
     * Ajoute en base 200 codes générés aléatoirement
     */
    public void createDatasetCodeDebug() {
        Random r = new Random();
        List<Long> codeAlreadyCreated = new ArrayList<>();
        long code;
        for (int i = 1; i < 6; i++) {
            Code c = new Code(Integer.toUnsignedLong(i), false);
            repoCode.save(c);
        }
    }
}
