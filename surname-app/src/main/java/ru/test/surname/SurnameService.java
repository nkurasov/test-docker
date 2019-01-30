package ru.test.surname;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Nikita Kurasov
 */
@RestController
public class SurnameService {

    private static final Logger logger = LoggerFactory.getLogger(SurnameService.class);

    @RequestMapping(path = "/surname", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> getSurname(@RequestParam("name") String name) {
        logger.info("Call with name {}", name);

        if ("Ivan".equals(name)) {
            return ResponseEntity.ok("Ivanov");
        } else if ("Nikita".equals(name)) {
            return ResponseEntity.ok("Petrov");
        }

        return ResponseEntity.badRequest().body("Unknown name: " + name);
    }
}
