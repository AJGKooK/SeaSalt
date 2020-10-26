package database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniClassDatabase extends JpaRepository<UniClass, Integer> {

}
