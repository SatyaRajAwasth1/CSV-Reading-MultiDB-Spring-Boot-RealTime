package np.com.esewa.learn.sampleapplication.inventory.service;

import np.com.esewa.learn.sampleapplication.filedetails.dto.CountDto;
import np.com.esewa.learn.sampleapplication.inventory.aspects.EncryptProductName;
import np.com.esewa.learn.sampleapplication.inventory.dto.ProductResponseDto;


import np.com.esewa.learn.sampleapplication.inventory.model.Product;
import np.com.esewa.learn.sampleapplication.inventory.model.ProductStatus;
import np.com.esewa.learn.sampleapplication.inventory.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> readProductDataFromFile(String filePath) {
        List<Product> productList = new ArrayList<>();
        BufferedReader bufferedReader;
        String line;
        try {

            bufferedReader = new BufferedReader(new FileReader(filePath));

            while ((line= bufferedReader.readLine()) != null){
                String [] row = line.split(",");
                Product product = new Product();
                // reading product derails form file
                product.setName(row[0]);
                product.setCode(row[1]);
                product.setQuantity(Long.parseLong(row[2]));
                product.setPrice(Long.parseLong(row[3]));

                productList.add(product); // converted csv data to list of product
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return productList;
    }

    @Override
    @EncryptProductName
    public CountDto addProduct(List<Product> productList) {
        CountDto countDto = new CountDto();
        int FAIL_COUNT = 0, SUCCESS_COUNT = 0;
        for (Product product : productList) {
            if (!productList.isEmpty()) {
                Product existingProduct = productRepository.findProductByCode(product.getCode());
                if (!(existingProduct == null)) {
                    if (existingProduct.getStatus() == ProductStatus.ACTIVE && existingProduct.getCode().equals(product.getCode())) {
                        FAIL_COUNT++;
                        continue;
                    }
                }
            }
                product.setStatus(ProductStatus.ACTIVE);
                productRepository.save(product);
                SUCCESS_COUNT++;
        }
        countDto.setFAIL_COUNT(FAIL_COUNT);
        countDto.setSUCCESS_COUNT(SUCCESS_COUNT);

        return countDto;
    }

    @Override
    public ProductResponseDto getProductByCode(String productCode) {
        Product product = productRepository.findProductByCode(productCode);
        ProductResponseDto responseProduct = new ProductResponseDto();
        if (product!=null){
            responseProduct.setProductCode(product.getCode());
            responseProduct.setProductName(Arrays.toString(Base64.getDecoder().decode(product.getName())));
            responseProduct.setQuantity(product.getQuantity());
            responseProduct.setPrice(product.getPrice());
        }
        return responseProduct;
    }

    @Override
    public boolean deleteProduct(Long id) {
        return false;
    }
}
