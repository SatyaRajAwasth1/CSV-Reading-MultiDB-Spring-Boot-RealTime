package np.com.esewa.learn.sampleapplication.filedetails.service;

import np.com.esewa.learn.sampleapplication.filedetails.dto.FileDetailsRequestDto;
import np.com.esewa.learn.sampleapplication.filedetails.model.FileStatus;
import np.com.esewa.learn.sampleapplication.filedetails.model.ProductFile;
import np.com.esewa.learn.sampleapplication.filedetails.repository.ProductFileRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductFileServiceImplTest {

    @Mock
    private ProductFileRepository testProductFileRepository;

    @InjectMocks
    private ProductFileServiceImpl testFileServiceImpl;


    @Test
    void saveFile() {
        //arrange
        FileDetailsRequestDto testFileDetailsRequestDto = new FileDetailsRequestDto();
        testFileDetailsRequestDto.setFilePath("D:\\Trainee\\week3\\files\\file1.csv");

        FileDetailsRequestDto anotherTestFileDetailsDTo = new FileDetailsRequestDto();
        anotherTestFileDetailsDTo.setFilePath("D:\\Trainee\\week3\\files\\file2.csv");

        ProductFile productFileToBeResponded = new ProductFile();
        productFileToBeResponded.setFilePath("D:\\Trainee\\week3\\files\\file1.csv");
        productFileToBeResponded.setStatus(FileStatus.PENDING);
        productFileToBeResponded.setFAIL_COUNT(0L);
        productFileToBeResponded.setSUCCESS_COUNT(0L);

        //act
        testFileServiceImpl.saveFile(testFileDetailsRequestDto);
        //testFileServiceImpl.saveFile(anotherTestFileDetailsDTo);

        //assert
        verify(testProductFileRepository,times(1)).save(productFileToBeResponded);


    }

    @Test
    void getProductFile() {
    }

    @Test
    void getFileById() {
    }

    @Test
    void processFile() {
    }
}