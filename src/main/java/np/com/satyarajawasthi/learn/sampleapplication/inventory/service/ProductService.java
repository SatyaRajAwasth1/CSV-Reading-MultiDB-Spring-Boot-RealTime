package np.com.satyarajawasthi.learn.sampleapplication.inventory.service;

import np.com.satyarajawasthi.learn.sampleapplication.filedetails.dto.CountDto;
import np.com.satyarajawasthi.learn.sampleapplication.inventory.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> readProductDataFromFile(String filePath);
    CountDto addProduct(List<Product> productList);
    boolean deleteProduct(Long id);
}
