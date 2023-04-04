package np.com.satyarajawasthi.learn.sampleapplication.inventory.repository;

import np.com.satyarajawasthi.learn.sampleapplication.inventory.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findProductByCode(String productCode);
}
