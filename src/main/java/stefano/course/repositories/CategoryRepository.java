package stefano.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import stefano.course.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    
}
