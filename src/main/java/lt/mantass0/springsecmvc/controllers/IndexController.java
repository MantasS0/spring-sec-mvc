package lt.mantass0.springsecmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.security.PermitAll;

@Controller
@RequestMapping("/")
public class IndexController {

    @PermitAll
    @GetMapping
    public String index() {
        return "index";
    }

}
