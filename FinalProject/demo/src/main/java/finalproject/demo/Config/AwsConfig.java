package finalproject.demo.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.lexruntimev2.LexRuntimeV2Client;
import software.amazon.awssdk.services.rekognition.RekognitionClient;

@Configuration
public class AwsConfig {

    @Value("${aws.accessKeyId}")
    private String accessKey;

    @Value("${aws.secretAccessKey}")
    private String secretKey;

    @Value("${aws.rekognitionRegion}")
    private String rekognitionRegion;

    @Value("${aws.lexRegion}")
    private String lexRegion;

    @Bean
    public RekognitionClient rekognitionClient() {
        return RekognitionClient.builder()
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(accessKey, secretKey)
                ))
                .region(Region.of(rekognitionRegion))
                .build();
    }

    @Bean
    public LexRuntimeV2Client lexRuntimeV2Client() {
        return LexRuntimeV2Client.builder()
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(accessKey, secretKey)
                ))
                .region(Region.of(lexRegion))
                .build();
    }
}
