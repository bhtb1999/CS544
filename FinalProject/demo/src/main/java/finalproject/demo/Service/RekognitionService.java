package finalproject.demo.Service;

import finalproject.demo.DTO.LabelDTO;
import finalproject.demo.DTO.TextDetectionDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.rekognition.RekognitionClient;
import software.amazon.awssdk.services.rekognition.model.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RekognitionService {

    private final RekognitionClient rekognitionClient;

    public RekognitionService(RekognitionClient rekognitionClient) {
        this.rekognitionClient = rekognitionClient;
    }

    public List<LabelDTO> getLabelsFromFile(MultipartFile file) throws IOException {
        DetectLabelsRequest request = DetectLabelsRequest.builder()
                .image(Image.builder()
                        .bytes(SdkBytes.fromByteArray(file.getBytes()))
                        .build())
                .maxLabels(5)
                .minConfidence(75F)
                .build();

        DetectLabelsResponse result = rekognitionClient.detectLabels(request);

        return result.labels().stream()
                .map(label -> new LabelDTO(label.name(), label.confidence()))
                .collect(Collectors.toList());
    }

    public List<TextDetectionDTO> detectTextFromFile(MultipartFile file) {
        DetectTextRequest request = DetectTextRequest.builder()
                .image(image -> {
                    try {
                        image.bytes(SdkBytes.fromByteArray(file.getBytes()));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .build();

        DetectTextResponse response = rekognitionClient.detectText(request);

        return response.textDetections().stream()
                .map(td -> new TextDetectionDTO(td.detectedText(), td.confidence(), td.typeAsString()))
                .collect(Collectors.toList());
    }


}
