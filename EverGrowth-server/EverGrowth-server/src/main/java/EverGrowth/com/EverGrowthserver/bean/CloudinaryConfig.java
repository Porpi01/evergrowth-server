package EverGrowth.com.EverGrowthserver.bean;

import java.io.File;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import io.github.cdimascio.dotenv.Dotenv;

@Configuration
public class CloudinaryConfig {

@Bean
public Cloudinary cloudinary() {
    String cloudName;
    String apiKey;
    String apiSecret;

    File envFile = new File(".env");
    if (envFile.exists()) {
        Dotenv dotenv = Dotenv.load();
        cloudName = dotenv.get("CLOUDINARY_CLOUD_NAME");
        apiKey = dotenv.get("CLOUDINARY_API_KEY");
        apiSecret = dotenv.get("CLOUDINARY_API_SECRET");
    } else {
        cloudName = System.getenv("CLOUDINARY_CLOUD_NAME");
        apiKey = System.getenv("CLOUDINARY_API_KEY");
        apiSecret = System.getenv("CLOUDINARY_API_SECRET");
    }

    return new Cloudinary(ObjectUtils.asMap(
            "cloud_name", cloudName,
            "api_key", apiKey,
            "api_secret", apiSecret
    ));
}
}
