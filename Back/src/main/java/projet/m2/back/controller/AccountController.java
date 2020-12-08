package projet.m2.back.controller;
import com.mysql.cj.xdevapi.JsonArray;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import projet.m2.back.entity.Account;
import projet.m2.back.service.IaccountService;
import org.json.simple.JSONObject;

@RestController
public class AccountController {

    @Autowired
    private IaccountService iaccountService;

    @GetMapping("/account/")
    public JSONObject info(@RequestHeader(value = "IdAccount") long idAccount) {
        Account a = iaccountService.getInfo(idAccount);
        JSONObject jsonInfo = new JSONObject();

        jsonInfo.put("NbDice", a.getNbDice());
        jsonInfo.put("Credit", a.getNbDice());
        jsonInfo.put("Prize", a.getPrize());
        jsonInfo.put("IndexSquarePurchased", a.getIndexSquarePurchased());

      return jsonInfo;
    }




}
