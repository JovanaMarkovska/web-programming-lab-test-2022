package mk.ukim.finki.wp.lab2022.repository;

import mk.ukim.finki.wp.lab2022.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {
    Optional<User> findByUsername(String username);
}
