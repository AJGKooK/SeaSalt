package app.database;

import app.database.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDatabase extends JpaRepository<User, String> {}