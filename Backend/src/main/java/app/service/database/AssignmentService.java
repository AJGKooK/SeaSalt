package app.service.database;

import app.database.entities.Assignment;
import app.database.AssignmentDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssignmentService {

    @Autowired
    AssignmentDatabase assignmentDatabase;

    public List<Assignment> getAllAssignments() {
        return assignmentDatabase.findAll();
    }

    public Optional<Assignment> getAssignmentById(Integer id) {
        return assignmentDatabase.findById(id);
    }

    public void saveAssignment(Assignment assignment) {
        assignmentDatabase.save(assignment);
    }
}
