package stefano.course.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import stefano.course.entities.Category;
import stefano.course.entities.Order;
import stefano.course.entities.OrderItem;
import stefano.course.entities.Product;
import stefano.course.entities.User;
import stefano.course.entities.enums.OrderStatus;
import stefano.course.repositories.CategoryRepository;
import stefano.course.repositories.OrderItemRepository;
import stefano.course.repositories.OrderRepository;
import stefano.course.repositories.ProductRepository;
import stefano.course.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    
    @Override
    public void run(String... args) throws Exception {

        Category c1 = new Category(null, "Electronics");
        Category c2 = new Category(null, "Books");

        categoryRepository.saveAll(Arrays.asList(c1, c2));

        User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
        User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");

        userRepository.saveAll(Arrays.asList(u1, u2));

        Order o1 = new Order(null, Instant.parse("2024-06-20T19:53:07Z"), OrderStatus.PAID ,u1);
        Order o2 = new Order(null, Instant.parse("2024-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT ,u2);
        Order o3 = new Order(null, Instant.parse("2024-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT ,u1);

        orderRepository.saveAll(Arrays.asList(o1, o2, o3));

        Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
        Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
        Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
        Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
        Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");

        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

        p1.getCategories().add(c1);
        p1.getCategories().add(c2);
        p2.getCategories().add(c1);
        p3.getCategories().add(c2);
        p4.getCategories().add(c1);
        p5.getCategories().add(c2);
        
        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

        OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());
        OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice());
        OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice());
        OrderItem oi4 = new OrderItem(o3, p5, 2, p5.getPrice());

        orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));
    }

}
