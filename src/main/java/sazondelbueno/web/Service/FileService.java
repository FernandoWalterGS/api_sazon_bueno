package sazondelbueno.web.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sazondelbueno.web.Utils.ApiResponse;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Service
public class FileService {

    private final S3Client s3Client;

    @Value("${aws.s3.bucket.name}")
    private String bucketName;

    @Value("${aws.cloudfront.domain}")
    private String cloudfrontDomain;

    public FileService(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public ApiResponse uploadFile(String key, MultipartFile file) throws IOException {
        String type = Objects.requireNonNull(file.getContentType()).split("/")[1];
        String fileName = UUID.randomUUID().toString() + "." + type;

        try {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(fileName)
                    .contentType(file.getContentType())
                    .build();

            s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));

            String fileUrl = "https://" + cloudfrontDomain + "/" + fileName;

            return new ApiResponse("File uploaded successfully!", fileUrl);
        } catch (S3Exception e) {
            throw new IOException("Failed to upload file to S3: " + e.getMessage(), e);
        }
    }
}
