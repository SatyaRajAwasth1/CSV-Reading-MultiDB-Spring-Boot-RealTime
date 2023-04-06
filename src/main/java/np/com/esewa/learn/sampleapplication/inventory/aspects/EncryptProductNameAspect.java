package np.com.esewa.learn.sampleapplication.inventory.aspects;

import np.com.esewa.learn.sampleapplication.inventory.model.Product;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

import java.util.Base64;
import java.util.List;

@Aspect
@Configuration
public class EncryptProductNameAspect {

    @Pointcut("@annotation(np.com.esewa.learn.sampleapplication.inventory.aspects.EncryptProductName)")
    void addProduct(){}

    @Pointcut("@annotation(np.com.esewa.learn.sampleapplication.inventory.aspects.DecryptProductName)")
    void getProductByCode(){}

    @Before("addProduct()")
    void encryptProductName(JoinPoint joinPoint){
        List<Product> productList = (List<Product>) joinPoint.getArgs()[0];
        // encoding the product names of all the product within list before saving
        for (Product product : productList) {
            product.setName(Base64.getEncoder().encodeToString(product.getName().getBytes()));
        }
    }

    @Before("getProductByCode()")
    Product decryptProductName(JoinPoint joinPoint){
         Product product = (Product) joinPoint.getArgs()[0];
        // encoding the product names of all the product within list before saving
        return null;
    }
}
