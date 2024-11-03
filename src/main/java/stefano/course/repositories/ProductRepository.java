package stefano.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import stefano.course.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
