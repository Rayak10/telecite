package technocite.tn.telecite.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestSecurityController {

    @GetMapping("/employe/get")
    public String getAdmin() {
        return "Hi admin";
    }

    @GetMapping("/scrummaster/get")
    public String getUser() {
        return "Hi user";
    }
}
