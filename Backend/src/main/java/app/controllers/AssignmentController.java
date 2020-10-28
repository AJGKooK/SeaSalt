package app.controllers;

import app.database.Assignment;
import app.excpetions.NotFoundException;
import app.service.AssignmentService;
import app.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(path = "/assignment")
public class AssignmentController {

    @Autowired
    SecurityService securityService;

    @Autowired
    AssignmentService assignmentService;

    @GetMapping(path = "/info")
    public Assignment info(@RequestParam String username, @RequestParam String password, @RequestParam Integer id) {
        Optional<Assignment> assignment = assignmentService.getAssignmentById(id);
        if(assignment.isPresent()) {
            securityService.isAuthorizedHttp(username, password, assignment.get().getAssignmentCourse());
            return assignment.get();
        } else {
            securityService.isAuthorizedHttp(username, password);
            throw new NotFoundException();
        }
    }
}
