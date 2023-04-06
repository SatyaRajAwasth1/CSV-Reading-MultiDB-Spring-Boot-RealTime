package np.com.satyarajawasthi.learn.sampleapplication.filedetails.service;


<<<<<<< HEAD:src/main/java/np/com/esewa/learn/sampleapplication/filedetails/service/ProductFileServiceImpl.java
import np.com.esewa.learn.sampleapplication.filedetails.dto.CountDto;
import np.com.esewa.learn.sampleapplication.filedetails.dto.FileDetailsRequestDto;
import np.com.esewa.learn.sampleapplication.filedetails.dto.FileDetailsResponseDto;
import np.com.esewa.learn.sampleapplication.filedetails.model.FileStatus;
import np.com.esewa.learn.sampleapplication.filedetails.model.ProductFile;
import np.com.esewa.learn.sampleapplication.filedetails.repository.ProductFileRepository;
import np.com.esewa.learn.sampleapplication.inventory.model.Product;
import np.com.esewa.learn.sampleapplication.inventory.service.ProductService;
import np.com.esewa.learn.sampleapplication.thirdpartyservices.resources.NotifyDto;
import np.com.esewa.learn.sampleapplication.thirdpartyservices.service.UserNotificationService;
=======
import np.com.satyarajawasthi.learn.sampleapplication.filedetails.dto.CountDto;
import np.com.satyarajawasthi.learn.sampleapplication.filedetails.dto.FileDetailsRequestDto;
import np.com.satyarajawasthi.learn.sampleapplication.filedetails.dto.FileDetailsResponseDto;
import np.com.satyarajawasthi.learn.sampleapplication.filedetails.model.FileStatus;
import np.com.satyarajawasthi.learn.sampleapplication.filedetails.model.ProductFile;
import np.com.satyarajawasthi.learn.sampleapplication.filedetails.repository.ProductFileRepository;
import np.com.satyarajawasthi.learn.sampleapplication.inventory.model.Product;
import np.com.satyarajawasthi.learn.sampleapplication.inventory.service.ProductService;
>>>>>>> 1adee7a73aaf50255d30eb3d532c6450825ecd9a:src/main/java/np/com/satyarajawasthi/learn/sampleapplication/filedetails/service/ProductFileServiceImpl.java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductFileServiceImpl implements ProductFileService{

    @Autowired
    private  ProductFileRepository productFileRepository;
    @Autowired
    private ProductService productService;

    @Autowired
    private UserNotificationService userNotificationService;
    @Override
    public void saveFile(FileDetailsRequestDto fileDetailsRequestDto) {
        ProductFile productFile = new ProductFile();
        productFile.setFilePath(fileDetailsRequestDto.getFilePath());
        productFile.setStatus(FileStatus.PENDING);
        productFile.setFAIL_COUNT(0L);
        productFile.setSUCCESS_COUNT(0L);


        productFileRepository.save(productFile);
    }

    @Override
    public ProductFile getProductFile(Long id){
        return productFileRepository.getReferenceById(id);
    }

    @Override
    public FileDetailsResponseDto getFileById(Long filePathId){
        FileDetailsResponseDto fileDetailsResponseDto = new FileDetailsResponseDto();

        ProductFile productFile = productFileRepository.findById(filePathId) .orElseThrow();
        fileDetailsResponseDto.setFilePath(productFile.getFilePath());
        fileDetailsResponseDto.setStatus(productFile.getStatus());

        return fileDetailsResponseDto;
    }

    @Scheduled(fixedRate = 120000) // repeats task automatically within 2 minutes
    // use fixedDelay to repeat the task after two minutes of first task finished
    // use fixedDelay with time of 1 minute or around to see the status pending, processing and completed for file config.
    void processFile(){
        List<ProductFile> productFileList = productFileRepository.findAllByStatus(FileStatus.PENDING);
        for (ProductFile productFile : productFileList) {
            productFile.setStatus(FileStatus.PROCESSING);
            productFileRepository.save(productFile); // save the updated file status
           List<Product> productList = productService.readProductDataFromFile(productFile.getFilePath());
           CountDto countDto = productService.addProduct(productList);
           // updating count of success and fail
           productFile.setFAIL_COUNT(countDto.getFAIL_COUNT());
           productFile.setSUCCESS_COUNT(countDto.getSUCCESS_COUNT());
           // file processing completed
           productFile.setStatus(FileStatus.COMPLETED);
           productFileRepository.save(productFile);

            NotifyDto notifyDto = new NotifyDto();
            notifyDto.setFAIL_COUNT(productFile.getFAIL_COUNT());
            notifyDto.setSUCCESS_COUNT(productFile.getSUCCESS_COUNT());
            notifyDto.setCsvFileName(productFile.getFilePath());
            userNotificationService.notify(notifyDto); // sending user notification

        }

    }
}
