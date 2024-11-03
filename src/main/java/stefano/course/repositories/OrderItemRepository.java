package stefano.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import stefano.course.entities.OrderItem;
import stefano.course.entities.pk.OrderItemPK;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {
    
}