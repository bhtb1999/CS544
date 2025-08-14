package finalproject.demo.DTO;

public class TextDetectionDTO {
    private String detectedText;
    private Float confidence;
    private String type; // LINE or WORD

    public TextDetectionDTO(String detectedText, Float confidence, String type) {
        this.detectedText = detectedText;
        this.confidence = confidence;
        this.type = type;
    }

    public String getDetectedText() {
        return detectedText;
    }

    public void setDetectedText(String detectedText) {
        this.detectedText = detectedText;
    }

    public Float getConfidence() {
        return confidence;
    }

    public void setConfidence(Float confidence) {
        this.confidence = confidence;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
