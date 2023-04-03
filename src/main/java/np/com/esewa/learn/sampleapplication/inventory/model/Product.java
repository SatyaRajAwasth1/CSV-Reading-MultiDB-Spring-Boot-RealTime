package np.com.esewa.learn.sampleapplication.inventory.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String code;
    private Long quantity;
    private Long price;
    @Enumerated(EnumType.STRING)
    private ProductStatus status;
}
