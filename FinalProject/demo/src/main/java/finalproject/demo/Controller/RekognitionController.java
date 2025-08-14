package finalproject.demo.Controller;

import finalproject.demo.DTO.LabelDTO;
import finalproject.demo.DTO.TextDetectionDTO;
import finalproject.demo.Service.RekognitionService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.rekognition.model.Label;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/rekognition")
public class RekognitionController {

    private final RekognitionService rekognitionService;

    public RekognitionController(RekognitionService rekognitionService) {
        this.rekognitionService = rekognitionService;
    }

    @PostMapping("/labels/local")
        public List<LabelDTO> detectLabelsFromFile(@RequestParam("file") MultipartFile file) throws IOException {
        return rekognitionService.getLabelsFromFile(file);
    }

    @PostMapping("/text/local")
    public List<TextDetectionDTO> detectTextFromImage(@RequestParam("file") MultipartFile file) throws IOException {
        return rekognitionService.detectTextFromFile(file);
    }
}


