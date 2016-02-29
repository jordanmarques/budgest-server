package server.web;

import server.domain.Person;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    @RequestMapping("/person")
    public Person person(@RequestParam(value="firstName") String firstName,
                         @RequestParam(value="lastName") String lastName) {

        return new Person(1, firstName, lastName);
    }
}
