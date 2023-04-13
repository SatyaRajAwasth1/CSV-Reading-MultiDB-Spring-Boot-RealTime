package np.com.esewa.learn.sampleapplication.inventory.repository;

import np.com.esewa.learn.sampleapplication.inventory.model.Product;
import np.com.esewa.learn.sampleapplication.inventory.model.ProductStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @Mock
    ProductRepository underTestProductRepository;

    @Test
    protected void itShouldFindProductByCode() {
        Product testProduct = new Product();
        testProduct.setName("Laptop");
        testProduct.setCode("E14");
        testProduct.setPrice(50000L);
        testProduct.setQuantity(20L);
        testProduct.setStatus(ProductStatus.ACTIVE);
        System.out.println(testProduct.getCode());

        Mockito.when(underTestProductRepository.save(testProduct)).thenReturn(testProduct);
        Product product = underTestProductRepository.save(testProduct);
        System.out.println(product.getName());

        Product productToBeReturned = product;
        Mockito.when(underTestProductRepository.findProductByCode(product.getCode())).thenReturn(productToBeReturned);
        productToBeReturned = underTestProductRepository.findProductByCode(product.getCode());

        String productCode = productToBeReturned.getCode();

        System.out.println(productCode);
        assertEquals("E14",productCode);

    }

}