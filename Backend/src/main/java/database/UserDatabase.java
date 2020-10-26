package database;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserDatabase extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}