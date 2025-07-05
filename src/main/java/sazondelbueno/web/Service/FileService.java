package sazondelbueno.web.Service;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sazondelbueno.web.Utils.ApiResponse;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FileService {

    @Autowired
    private Storage storage;

    @Value("${gcp.bucket.name}")
    private String bucketName;

    @Value("${gcp.bucket.url}")
    private String bucketUrl;

    public ApiResponse uploadFile(String key, MultipartFile file) throws IOException {
        String type = Objects.requireNonNull(file.getContentType()).split("/")[1];
        String fileName = UUID.randomUUID().toString() + "." + type;

        BlobId blobId = BlobId.of(bucketName, fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(file.getContentType()).build();
        storage.create(blobInfo, file.getBytes());

        return new ApiResponse("File uploaded successfully!", bucketUrl + "/" + fileName);
    }
}