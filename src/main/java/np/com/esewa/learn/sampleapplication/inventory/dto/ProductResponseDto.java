package np.com.esewa.learn.sampleapplication.inventory.dto;


public record ProductResponseDto (
        String productName,
        String productCode,
        long quantity,
        long price
    ) {

}
