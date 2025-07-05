package sazondelbueno.web.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sazondelbueno.web.Service.FileService;
import sazondelbueno.web.Utils.ApiResponse;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/storage")
@CrossOrigin("*")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<ApiResponse> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            ApiResponse response = fileService.uploadFile(file.getOriginalFilename(), file);
            return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
        } catch (IOException e) {
            ApiResponse response = new ApiResponse("Error uploading file: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<ApiResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}
