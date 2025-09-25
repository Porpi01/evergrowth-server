package EverGrowth.com.EverGrowthserver.api;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import EverGrowth.com.EverGrowthserver.service.CloudinaryService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/media/cloudinary")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CloudinaryController {

    private final CloudinaryService cloudinaryService;

    @PostMapping("/")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) {
        try {
            String url = cloudinaryService.uploadFile(file);
            return ResponseEntity.ok(Map.of("url", url));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", e.getMessage()));
        }
    }
}