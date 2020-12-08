package projet.m2.back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import projet.m2.back.entity.Account;
import projet.m2.back.service.IaccountService;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AccountController {

    @Autowired
    private IaccountService iaccountService;

    @GetMapping("/account/")
    public String info(@RequestHeader(value = "IdAccount") long idAccount) {

        iaccountService.getInfo(idAccount);
      return null;
    }




}
