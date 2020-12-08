package projet.m2.back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import projet.m2.back.service.interfaces.ICodeService;

@RestController
public class CodeController {

    @Autowired
    private ICodeService codeService;

    @PostMapping("/code/use/{code}")
    public int useCode(@RequestHeader(value = "IdAccount") long idAccount, @PathVariable String code) {
        return codeService.useCode(idAccount, code);
    }


}
