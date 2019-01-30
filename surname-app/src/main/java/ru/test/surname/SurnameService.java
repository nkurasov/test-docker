package ru.test.surname;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Nikita Kurasov
 */
@RestController
public class SurnameService {

    @RequestMapping(path = "/surname", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> getSurname(@RequestParam("name") String name) {
        if ("Ivan".equals(name)) {
            return ResponseEntity.ok("Ivanov");
        } else if ("Nikita".equals(name)) {
            return ResponseEntity.ok("Petrov");
        }

        return ResponseEntity.badRequest().body("Unknown name: " + name);
    }
}
