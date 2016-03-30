package server.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import server.model.Bugest;
import server.repository.BugestRepository;

import java.util.List;

public class BugestController {

    @Autowired
    BugestRepository bugestRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<Bugest> getAll() {
        return bugestRepository.findAll();
    }
}
