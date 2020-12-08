package projet.m2.back.controller;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projet.m2.back.entity.Account;
import projet.m2.back.service.IAccountService;
import org.json.simple.JSONObject;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@RestController
public class AccountController {

    @Autowired
    private IAccountService iaccountService;

    @GetMapping("/account")
    public JSONObject info(@RequestHeader(value = "IdAccount") long idAccount) {
        Account a = iaccountService.getInfo(idAccount);
        JSONObject jsonInfo = new JSONObject();

        jsonInfo.put("NbDice", a.getNbDice());
        jsonInfo.put("Credit", a.getNbDice());
        jsonInfo.put("Prize", a.getPrize());
        jsonInfo.put("IndexSquarePurchased", a.getIndexSquarePurchased());

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
                        return ResponseEntity.status(200).body(account);
                    }
                } catch (UnsupportedEncodingException exception) {
                    exception.printStackTrace();
                }
            }
        }
        JSONObject jsonError = new JSONObject();
        jsonError.put("message", "Error: autorization error");
        return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body(jsonError);
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
                    return ResponseEntity.status(200).body(account);
                } else {
                    JSONObject jsonError = new JSONObject();
                    jsonError.put("message", "Error: erreur de création du compte");
                    return ResponseEntity.status(200).body(jsonError);
                }
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }
        JSONObject jsonError = new JSONObject();
        jsonError.put("message", "Error: Une erreur inattendue est survenue.");
        return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body(jsonError);
    }

    @PostMapping("/account/dice")
    public void throwDice(@RequestHeader(value = "IdCompte") long id) {
        iaccountService.throwDice(id);
    }

}
