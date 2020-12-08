package projet.m2.back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.m2.back.entity.Code;
import projet.m2.back.repository.CodeRepository;

import java.text.ParseException;

@Service
public class CodeServiceImpl implements IcodeService {

    @Autowired
    CodeRepository repoCode;

    @Autowired
    IaccountService accountService;

    @Override
    public int useCode(String idAccount, String code) {
        int codeRetour;
        try {
            long codeLong = Long.parseLong(code);
            Code codeBDD = repoCode.findByCode(codeLong);
            if (!codeBDD.isUsed()) {
                //Account account = accountService.
                codeRetour = 0;
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
