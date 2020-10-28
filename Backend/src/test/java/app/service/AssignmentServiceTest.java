package app.service;

import app.database.Assignment;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Optional;

public class AssignmentServiceTest {

    AssignmentService service;

    @Mock
    Assignment assignment;

    @Test
    public void canFindCourse()
    {
        service.addAssignment(assignment);

        Optional<Assignment> optionalAssignment = service.getAssignmentById(assignment.getAssignmentId());

        Assert.assertTrue(optionalAssignment.isPresent());
        Assert.assertEquals(optionalAssignment.get(), assignment);
    }
}