package np.com.satyarajawasthi.learn.sampleapplication.filedetails.controller;

import np.com.satyarajawasthi.learn.sampleapplication.filedetails.dto.FileDetailsRequestDto;
import np.com.satyarajawasthi.learn.sampleapplication.filedetails.dto.FileDetailsResponseDto;
import np.com.satyarajawasthi.learn.sampleapplication.filedetails.service.ProductFileService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/files/")
public class ProductFileController {
    private final ProductFileService productFileService;

    public ProductFileController(ProductFileService productFileService) {
        this.productFileService = productFileService;
    }

    @PostMapping("/add")
    void addFile(@RequestBody FileDetailsRequestDto fileDetailsRequestDto){
        productFileService.saveFile(fileDetailsRequestDto);
    }

    @GetMapping("/{filePathId}")
    FileDetailsResponseDto addFile(@PathVariable Long filePathId){
      return   productFileService.getFileById(filePathId);
    }
}
