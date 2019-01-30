package ru.test.hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Nikita Kurasov
 */
@RestController
public class HelloService {

    private static final Logger logger = LoggerFactory.getLogger(HelloService.class);

    private SurnameService surnameService;

    @RequestMapping(path = "/hello", method = RequestMethod.GET)
    public String sayHello(String name) {
        logger.info("Call with name {}", name);
        String surname = surnameService.getSurname(name);
        if (surname != null) {
            return "Hello, " + surname + " " + name + "!";
        }

        return "Hello, " + name + "!";
    }

    @Autowired
    public void setSurnameService(SurnameService surnameService) {
        this.surnameService = surnameService;
    }
}
