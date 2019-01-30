package ru.test.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Nikita Kurasov
 */
@RestController
public class HelloService {

    private SurnameService surnameService;

    @RequestMapping(path = "/hello", method = RequestMethod.GET)
    public String sayHello(String name) {
        String surname = surnameService.getSurname(name);
        return "Hello, " + surname + " " + name + "!";
    }

    @Autowired
    public void setSurnameService(SurnameService surnameService) {
        this.surnameService = surnameService;
    }
}
