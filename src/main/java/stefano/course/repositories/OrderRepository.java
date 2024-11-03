package stefano.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import stefano.course.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    
}
