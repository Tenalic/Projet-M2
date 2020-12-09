package projet.m2.back.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projet.m2.back.entity.Account;
import projet.m2.back.service.interfaces.ICodeService;

@RestController
public class CodeController {

    @Autowired
    private ICodeService codeService;

    @PostMapping("/code/use/{code}")
    public ResponseEntity useCode(@RequestHeader(value = "IdAccount") long idAccount, @PathVariable String code) {

        Object response = codeService.useCode(idAccount, code);

        JSONObject responseJSON = new JSONObject();

        if (response instanceof Integer) {
            Integer codeErreur = (Integer) response;
            switch (codeErreur) {
                case 1:
                    responseJSON.put("message", "Error: Code non trouvé");
                    break;
                case 2:
                    responseJSON.put("message", "Error: Code déjà utilisé");
                    break;
                case 3:
                    responseJSON.put("message", "Error: Code inconue");
                    break;
                default:
                    responseJSON.put("message", "Error: Erreur inconue");
                    break;
            }
            return ResponseEntity.status(200).body(responseJSON);
        } else {
            Account account = (Account) response;
            return ResponseEntity.status(200).body(account);
        }
    }


}
