package np.com.esewa.learn.sampleapplication.inventory.service;

import np.com.esewa.learn.sampleapplication.filedetails.dto.CountDto;
import np.com.esewa.learn.sampleapplication.inventory.dto.ProductResponseDto;
import np.com.esewa.learn.sampleapplication.inventory.model.Product;

import java.util.List;


public interface ProductService {

    List<Product> readProductDataFromFile(String filePath);
    CountDto addProduct(List<Product> productList);

    ProductResponseDto getProductByCode(String productCode);
    boolean deleteProduct(Long id);
}
