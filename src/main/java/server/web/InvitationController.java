package server.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import server.model.Budget;
import server.model.Invitation;
import server.repository.InvitationRepository;
import server.service.BudgetService;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/invit")
public class InvitationController {

    @Autowired
    InvitationRepository invitationRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<Invitation> getAll() {
        return invitationRepository.findAll();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Invitation getById(@PathVariable("id") Long id){
        return  invitationRepository.findOne(id);
    }

    @RequestMapping(path = "person/{personId}", method = RequestMethod.GET)
    public List<Invitation> getByPersonId(@PathVariable("personId") String personId){
        return  invitationRepository.findByPersonId(personId);
    }

    @RequestMapping(method = POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Invitation addBudget(@RequestBody Invitation invitation) throws Exception {
        return invitationRepository.save(invitation);
    }

    @RequestMapping(path = "/{id}", method = DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteBudget(@PathVariable("id") Long id){
        invitationRepository.delete(id);
    }

}
