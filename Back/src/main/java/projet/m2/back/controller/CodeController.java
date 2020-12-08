package projet.m2.back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import projet.m2.back.service.IcodeService;

@RestController
public class CodeController {

    @Autowired
    private IcodeService codeService;

    @PostMapping("/code/use/{code}")
    public int useCode(@RequestHeader(value = "IdAccount") String idAccount, @PathVariable String code) {
        return codeService.useCode(idAccount, code);
    }
}
