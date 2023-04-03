package np.com.esewa.learn.sampleapplication.filedetails.service;

import np.com.esewa.learn.sampleapplication.filedetails.dto.FileDetailsRequestDto;
import np.com.esewa.learn.sampleapplication.filedetails.dto.FileDetailsResponseDto;
import np.com.esewa.learn.sampleapplication.filedetails.model.FileStatus;
import np.com.esewa.learn.sampleapplication.filedetails.model.ProductFile;

import java.util.List;

public interface ProductFileService {
    void saveFile(FileDetailsRequestDto fileDetailsRequestDto);
    FileDetailsResponseDto getFileById(Long filePathId);
    ProductFile getProductFile(Long id);
}
