package np.com.esewa.learn.sampleapplication.inventory.service;

import np.com.esewa.learn.sampleapplication.filedetails.dto.CountDto;
import np.com.esewa.learn.sampleapplication.inventory.dto.ProductResponseDto;
import np.com.esewa.learn.sampleapplication.inventory.model.Product;
import np.com.esewa.learn.sampleapplication.inventory.model.ProductStatus;
import np.com.esewa.learn.sampleapplication.inventory.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @Mock
    private ProductRepository underTestProductRepository;

    @InjectMocks
    private ProductServiceImpl underTestProductServiceImpl;

    @Test
    void itShouldFindProductByCode(){
        Product testProduct = new Product();
        testProduct.setName(Base64.getEncoder().encodeToString(("Laptop").getBytes()));
        testProduct.setCode("E14");
        testProduct.setPrice(50000L);
        testProduct.setQuantity(20L);
        testProduct.setStatus(ProductStatus.ACTIVE);
        System.out.println(testProduct.getCode());

        Mockito.when(underTestProductRepository.findProductByCode(testProduct.getCode())).thenReturn(testProduct);
        ProductResponseDto productResponseDto = underTestProductServiceImpl.getProductByCode(testProduct.getCode());
        System.out.println(testProduct.getName());

        String responseProductName = productResponseDto.getProductName();


        assertEquals("L" +
                "aptop",responseProductName);


        System.out.println(responseProductName);

    }

    @Test
    void itShouldReadDataFromFile(){
        String pathOfFileToBeRead = "D:\\Trainee\\week3\\files\\file1.csv";
        List<Product> listOfProductReadFromFile = new ArrayList<>();
        listOfProductReadFromFile = underTestProductServiceImpl.readProductDataFromFile(pathOfFileToBeRead);

        assertEquals("e15",listOfProductReadFromFile.get(0).getCode());
        assertEquals(150000,listOfProductReadFromFile.get(1).getPrice());


        for (Product product : listOfProductReadFromFile) {
            System.out.println("Product name: " + product.getName());
            System.out.println("Product code: " + product.getCode());
            System.out.println("Product quantity: " + product.getQuantity());
            System.out.println("Product price: " + product.getPrice());
            System.out.println();
        }

    }

    @Test
    void itShouldAddProductByListReadFromFile(){
        Product product1 = new Product();
        product1.setName("Mobile");
        product1.setCode("MB1");
        product1.setPrice(20000L);
        product1.setQuantity(10L);
        product1.setStatus(ProductStatus.ACTIVE);

        Product product2 = new Product();
        product2.setName("Laptop");
        product2.setCode("LP1");
        product2.setPrice(200000L);
        product2.setQuantity(10L);
        product2.setStatus(ProductStatus.ACTIVE);

        Product product3 = new Product();
        product3.setName("Mobile Phone");
        product3.setCode("MB1");
        product3.setPrice(20000L);
        product3.setQuantity(10L);
        product3.setStatus(ProductStatus.ACTIVE);

        List<Product> listOfProductToBeAdded = new ArrayList<>();
        listOfProductToBeAdded.add(product1);
        listOfProductToBeAdded.add(product2);
        listOfProductToBeAdded.add(product3);

        Mockito.when(underTestProductRepository.save(listOfProductToBeAdded.get(0))).thenReturn(product1);
        Mockito.when(underTestProductRepository.save(listOfProductToBeAdded.get(1))).thenReturn(product2);
        Mockito.when(underTestProductRepository.save(listOfProductToBeAdded.get(2))).thenReturn(product3);

        CountDto countDto = underTestProductServiceImpl.addProduct(listOfProductToBeAdded);
        verify(underTestProductRepository).save(product3); // verifies whether save(product1) is called

        assertEquals(3,countDto.getSUCCESS_COUNT());
    }
}
