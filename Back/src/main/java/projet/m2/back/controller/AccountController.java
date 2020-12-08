package projet.m2.back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projet.m2.back.entity.Account;
import projet.m2.back.service.IaccountService;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@RestController
public class AccountController {

    @Autowired
    IaccountService iaccountService;

    @GetMapping("/account/")
    public String info(@RequestHeader(value = "IdAccount") long idAccount) {

        iaccountService.getInfo(idAccount);
      return null;
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
        return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body("{ \"error\": \"autorization error\" }");
    }

    @PostMapping("/account/create")
    public ResponseEntity createAccount(@RequestBody Account account) {
        return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body("{ \"error\": \"autorization error\" }");
    }
}
