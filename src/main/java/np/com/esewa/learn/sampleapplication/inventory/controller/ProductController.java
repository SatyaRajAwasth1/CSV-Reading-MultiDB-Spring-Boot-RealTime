package np.com.esewa.learn.sampleapplication.inventory.controller;

import np.com.esewa.learn.sampleapplication.inventory.dto.ProductResponseDto;
import np.com.esewa.learn.sampleapplication.inventory.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products/{productCode}")
    ProductResponseDto getProductByCode(@PathVariable String productCode) {
        return productService.getProductByCode(productCode);
    }
}
