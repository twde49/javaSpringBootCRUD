package dev.esdlyon.mobilePhoneApp.Repository;

import dev.esdlyon.mobilePhoneApp.Entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
