package np.com.satyarajawasthi.learn.sampleapplication.filedetails.dto;

import lombok.Getter;
import lombok.Setter;
import np.com.satyarajawasthi.learn.sampleapplication.filedetails.model.FileStatus;

@Getter
@Setter
public class FileDetailsResponseDto {
    private String filePath;
    private FileStatus status;
}
