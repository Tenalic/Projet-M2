package projet.m2.back.controller;
import com.mysql.cj.xdevapi.JsonArray;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projet.m2.back.entity.Account;
import projet.m2.back.service.IaccountService;
import org.json.simple.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@RestController
public class AccountController {

    @Autowired
    private IaccountService iaccountService;

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
                    if(account != null){
                        return ResponseEntity.status(200).body(account);
                    }
                } catch (UnsupportedEncodingException exception) {
                    exception.printStackTrace();
                }
            }
        }
        JSONObject jsonError = new JSONObject();
        jsonError.put("error", "autorization error");
        return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body(jsonError);
    }

    @PostMapping("/account/create")
    public ResponseEntity createAccount(@RequestBody Account account) {
        if(account != null){
            //TODO DEBUG
            System.out.println(account);
        }
        JSONObject jsonError = new JSONObject();
        jsonError.put("error", "autorization error");
        return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body(jsonError);
    }
}
