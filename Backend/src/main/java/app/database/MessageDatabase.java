package app.database;

import app.database.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageDatabase extends JpaRepository<Message, Integer> {}
