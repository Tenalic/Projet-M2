package projet.m2.back.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projet.m2.back.constant.Constant;
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
                    responseJSON.put("message", "Error: Compte non trouvé");
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
            return ResponseEntity.status(200).contentType(MediaType.valueOf(Constant.MEDIATYPE_JSON)).body(responseJSON);
        } else {
            Object[] tabObj = (Object[]) response;
            Account account = (Account) tabObj[0];
            String prize = (String)tabObj[1];
            int creditWin = (int)tabObj[2];
            if(creditWin != 0){
                responseJSON.put("creditWin", creditWin);
                responseJSON.put("credit", account.getCredit());
            }
            if(prize != null){
                responseJSON.put("prizeWin", prize);
                responseJSON.put("prize", account.getPrize());
            }
            responseJSON.put("nbDice", account.getNbDice());

            return ResponseEntity.status(200).contentType(MediaType.valueOf(Constant.MEDIATYPE_JSON)).body(responseJSON);
        }
    }


}
