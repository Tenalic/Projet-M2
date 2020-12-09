package projet.m2.back.controller;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projet.m2.back.constant.Constant;
import projet.m2.back.entity.Account;
import projet.m2.back.entity.Prize;
import projet.m2.back.service.interfaces.IAccountService;
import org.json.simple.JSONObject;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;

@RestController
public class AccountController {

    @Autowired
    private IAccountService iaccountService;

    @GetMapping("/account")
    public JSONObject info(@RequestHeader(value = "IdAccount") long idAccount) {
        JSONObject jsonInfo = new JSONObject();

        if(iaccountService.accountExistsById(idAccount)){
            Account a = iaccountService.getInfo(idAccount);

            jsonInfo.put("NbDice", a.getNbDice());
            jsonInfo.put("Credit", a.getCredit());
            jsonInfo.put("Prize", a.getPrize());
            jsonInfo.put("IndexSquarePurchased", a.getIndexSquarePurchased());
            jsonInfo.put("IndexSquare", a.getIndexSquare());

        } else {
            jsonInfo.put("message", "Error: Account not found");
        }
        return jsonInfo;
    }

    @DeleteMapping("/account")
    public JSONObject deleteAccount(@RequestHeader(value = "IdAccount") long id) {
        JSONObject myJSON = new JSONObject();
        if (!iaccountService.accountExistsById(id)) {
            myJSON.put("message", "Error: Account does not exist");
        } else {
            iaccountService.deleteAccount(id);
            System.out.println(iaccountService.accountExistsById(id));
            if (iaccountService.accountExistsById(id)) {
                myJSON.put("message", "Error: Account not deleted");
            } else {
                myJSON.put("message", "Account deleted successfully");
            }
        }
        return myJSON;
    }

    @PostMapping("/account/connection")
    public ResponseEntity connection(@RequestHeader(name = "Authorization") String authorization) {
        if (authorization != null && !authorization.isBlank() && !authorization.isEmpty()) {
            String[] split = authorization.split(" ");
            if (split[0].equals("Basic")) {
                byte[] decodedValue = Base64.getDecoder().decode(split[1]);
                try {
                    String undecodeBase64String = new String(decodedValue, StandardCharsets.UTF_8.toString());
                    String[] uncodeSplitString = undecodeBase64String.split(":");
                    Account account = iaccountService.connection(uncodeSplitString[0], uncodeSplitString[1]);
                    if (account != null) {
                        return ResponseEntity.status(201).body(account);
                    }
                } catch (UnsupportedEncodingException exception) {
                    exception.printStackTrace();
                }
            }
        }
        JSONObject jsonError = new JSONObject();
        jsonError.put("message", "Error: autorization error");
        return ResponseEntity.status(400).contentType(MediaType.valueOf(Constant.MEDIATYPE_JSON)).headers(new HttpHeaders()).body(jsonError);
    }

    @PostMapping("/account/create")
    public ResponseEntity createAccount(@RequestBody String accountBody) {
        if (accountBody != null) {
            JSONParser parser = new JSONParser();
            try {
                JSONObject accountJson = (JSONObject) parser.parse(accountBody);
                String lastname = (String) accountJson.get("lastname");
                String firstname = (String) accountJson.get("firstname");
                String nickname = (String) accountJson.get("nickname");
                String email = (String) accountJson.get("email");
                String password = (String) accountJson.get("password");
                Account account = iaccountService.creationAccount(email, lastname, firstname, nickname, password);
                if (account != null) {
                    return ResponseEntity.status(400).contentType(MediaType.valueOf(Constant.MEDIATYPE_JSON)).body(account);
                } else {
                    JSONObject jsonError = new JSONObject();
                    jsonError.put("message", "Error: erreur de création du compte");
                    return ResponseEntity.status(400).contentType(MediaType.valueOf(Constant.MEDIATYPE_JSON)).body(jsonError);
                }
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }
        JSONObject jsonError = new JSONObject();
        jsonError.put("message", "Error: Une erreur inattendue est survenue.");
        return ResponseEntity.status(201).contentType(MediaType.valueOf(Constant.MEDIATYPE_JSON)).body(jsonError);
    }

    @PostMapping("/account/dice")
    public ResponseEntity throwDice(@RequestHeader(value = "IdAccount") long id) {
        Object o = iaccountService.throwDice(id);
        if (o instanceof Object[]) {
            Object[]  tabO = (Object[])o;
            Account a = (Account) tabO[0];
            int[] diceResult = (int[]) tabO[1];
            JSONObject jsonResult = new JSONObject();
            jsonResult.put("nbDice", a.getNbDice());
            jsonResult.put("indexSquare", a.getIndexSquare());
            jsonResult.put("diceResult", diceResult);
            return ResponseEntity.status(200).contentType(MediaType.valueOf(Constant.MEDIATYPE_JSON)).body(jsonResult);
        }
        if(o instanceof Integer){
            JSONObject responseJSON = new JSONObject();
            Integer errorCode = (Integer) o;
            switch (errorCode) {
                case -1:
                    responseJSON.put("message", "Error: Compte non trouvé");
                    break;
                case -2:
                    responseJSON.put("message", "Error: nombre de dé insuffisant");
                    break;
                case -3:
                    responseJSON.put("message", "Error: board non trouvé");
                    break;
                default:
                    responseJSON.put("message", "Error: Erreur inconue");
                    break;
            }
            return ResponseEntity.status(200).contentType(MediaType.valueOf(Constant.MEDIATYPE_JSON)).body(responseJSON);

        }
        //TODO CHECK ERROR CODE
        JSONObject jsonError = new JSONObject();
        jsonError.put("message", "Error: Une erreur inattendue est survenue.");
        return ResponseEntity.status(200).contentType(MediaType.valueOf(Constant.MEDIATYPE_JSON)).body(jsonError);
    }

    @PostMapping("/account/buy")
    public ResponseEntity buySquare(@RequestHeader(value = "IdAccount") long id) {
        JSONObject responseJSON = new JSONObject();
        if (iaccountService.buySquare(id)) {
            Account a = iaccountService.getInfo(id);
            Prize prizeWin = iaccountService.checkSquareColorWinner(id);
            if (prizeWin != null) {
                responseJSON.put("prizeWin", prizeWin);
                responseJSON.put("prize", a.getPrize());
            }
            responseJSON.put("indexSquarePurchased", a.getIndexSquarePurchased());
            responseJSON.put("credit", a.getCredit());
        }
        return ResponseEntity.status(200).contentType(MediaType.valueOf(Constant.MEDIATYPE_JSON)).body(responseJSON);
    }


    @PutMapping("/account/update")
    public ResponseEntity updateAccount(@RequestBody String updateAccountBody, @RequestHeader(value = "IdAccount") long id) {
        if (updateAccountBody != null) {
            JSONParser parser = new JSONParser();
            JSONObject jsonError = new JSONObject();
            try {
                JSONObject accountJson = (JSONObject) parser.parse(updateAccountBody);
                String lastname = (String) accountJson.get("lastname");
                String firstname = (String) accountJson.get("firstname");
                String nickname = (String) accountJson.get("nickname");
                HashMap<String, String> listModifyValue = new HashMap<String, String>();

                if(lastname.isBlank() || firstname.isBlank() || nickname.isBlank()) {
                    jsonError.put("message", "Error: Un des champs est vide");
                    return ResponseEntity.status(400).contentType(MediaType.valueOf(Constant.MEDIATYPE_JSON)).body(jsonError);
                }

                listModifyValue.put("lastname", lastname);
                listModifyValue.put("firstname", firstname);
                if (!iaccountService.accountExistsByNickname(nickname))
                    listModifyValue.put("nickname", nickname);
                else {
                    jsonError.put("message", "Error: Pseudo déjà utilisé");
                    listModifyValue.put("message", "Error: Pseudo déjà utilisé");
                }

                Account account = iaccountService.modifyValue(listModifyValue, id);
                if (account != null) {
                    return ResponseEntity.status(200).contentType(MediaType.valueOf(Constant.MEDIATYPE_JSON)).body(listModifyValue);
                } else {
                    jsonError.put("message", "Error: compte inconnu");
                    return ResponseEntity.status(200).contentType(MediaType.valueOf(Constant.MEDIATYPE_JSON)).body(jsonError);
                }
            } catch (ParseException ex) {
                ex.printStackTrace();
            }

        }
        JSONObject jsonError = new JSONObject();
        jsonError.put("message", "Error: erreur inconnu");
        return ResponseEntity.status(200).contentType(MediaType.valueOf(Constant.MEDIATYPE_JSON)).body(jsonError);
    }
}
