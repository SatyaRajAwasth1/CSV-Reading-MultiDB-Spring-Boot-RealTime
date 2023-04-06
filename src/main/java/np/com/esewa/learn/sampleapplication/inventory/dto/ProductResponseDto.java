package np.com.esewa.learn.sampleapplication.inventory.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ProductResponseDto implements Serializable {
    private String productName;
    private String productCode;
    private long quantity;
    private long price;

}
