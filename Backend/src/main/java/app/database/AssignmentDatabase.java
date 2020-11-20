package app.database;

import app.database.entities.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentDatabase extends JpaRepository<Assignment, Integer> {
}