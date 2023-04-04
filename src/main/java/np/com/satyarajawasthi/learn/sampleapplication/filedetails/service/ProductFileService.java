package np.com.satyarajawasthi.learn.sampleapplication.filedetails.service;

import np.com.satyarajawasthi.learn.sampleapplication.filedetails.dto.FileDetailsRequestDto;
import np.com.satyarajawasthi.learn.sampleapplication.filedetails.dto.FileDetailsResponseDto;
import np.com.satyarajawasthi.learn.sampleapplication.filedetails.model.ProductFile;

public interface ProductFileService {
    void saveFile(FileDetailsRequestDto fileDetailsRequestDto);
    FileDetailsResponseDto getFileById(Long filePathId);
    ProductFile getProductFile(Long id);
}
