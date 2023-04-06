package np.com.esewa.learn.sampleapplication.inventory.repository;

import np.com.esewa.learn.sampleapplication.inventory.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findProductByCode(String productCode);
}
