package stefano.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import stefano.course.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
}
