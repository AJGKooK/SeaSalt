package app.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeworkDatabase extends JpaRepository<Homework, Integer> {

}