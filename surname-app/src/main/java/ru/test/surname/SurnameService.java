package ru.test.surname;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Nikita Kurasov
 */
@RestController
public class SurnameService {

    private static final Logger logger = LoggerFactory.getLogger(SurnameService.class);

    private Surnames surnames;

    @Autowired
    public void setSurnames(Surnames surnames) {
        this.surnames = surnames;
    }

    @RequestMapping(path = "/surname", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> getSurname(@RequestParam("name") String name) {
        logger.info("Call with name {}", name);

        return surnames.getSurname(name)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().body("Unknown name: " + name));
    }
}
