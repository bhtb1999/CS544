package finalproject.demo.DTO;

public class LabelDTO {
    private String name;
    private Float confidence;

    public LabelDTO(String name, Float confidence) {
        this.name = name;
        this.confidence = confidence;
    }

    public String getName() {
        return name;
    }

    public Float getConfidence() {
        return confidence;
    }
}
