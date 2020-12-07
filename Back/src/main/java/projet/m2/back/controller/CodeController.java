package projet.m2.back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import projet.m2.back.service.IcodeService;

@RestController("/code")
public class CodeController {

    @Autowired
    private IcodeService codeService;

    @PostMapping("/use/{code}")
    public String useCode(@RequestHeader(value = "Authorization") String authorization, @PathVariable String code) {
        codeService.useCode(authorization, code);
        return null;
    }
}
