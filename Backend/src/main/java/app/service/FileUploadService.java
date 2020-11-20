package app.service;

import app.excpetions.FileStorageException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileUploadService {

    @Value("${app.upload.dir:${user.home}}")
    public String uploadDir;

    public String uploadFile(Enum<UploadType> type, String id, MultipartFile file) {
        try {
            if (file.getOriginalFilename() == null) {
                String timestamp = String.valueOf(System.currentTimeMillis());
                Path path = Paths.get(uploadDir
                        + File.separator + "uploads"
                        + File.separator + type.toString().toLowerCase()
                        + File.separator + id
                        + File.separator + timestamp);
                if (!Files.exists(path)) {
                    Files.createDirectories(path);
                }
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                return timestamp;
            } else {
                Path path = Paths.get(uploadDir
                        + File.separator + "uploads"
                        + File.separator + type.toString().toLowerCase()
                        + File.separator + id
                        + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));
                if (!Files.exists(path)) {
                    Files.createDirectories(path);
                }
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                return StringUtils.cleanPath(file.getOriginalFilename());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new FileStorageException("Could not store file " + file.getOriginalFilename() + ". Please try again!");
        }
    }

    public String uploadFile(Enum<UploadType> type, String id, String username, MultipartFile file) {
        try {
            if (file.getOriginalFilename() == null) {
                String timestamp = String.valueOf(System.currentTimeMillis());
                Path path = Paths.get(uploadDir
                        + File.separator + "uploads"
                        + File.separator + type.toString().toLowerCase()
                        + File.separator + id
                        + File.separator + username
                        + File.separator + timestamp);
                if (!Files.exists(path)) {
                    Files.createDirectories(path);
                }
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                return timestamp;
            } else {
                Path path = Paths.get(uploadDir
                        + File.separator + "uploads"
                        + File.separator + type.toString().toLowerCase()
                        + File.separator + id
                        + File.separator + username
                        + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));
                if (!Files.exists(path)) {
                    Files.createDirectories(path);
                }
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                return StringUtils.cleanPath(file.getOriginalFilename());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new FileStorageException("Could not store file " + file.getOriginalFilename() + ". Please try again!");
        }
    }

    public void profileUpload(String username, MultipartFile file) {
        try {
            Path path = Paths.get(uploadDir
                    + File.separator + "profiles"
                    + File.separator + username + ".png");
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
            throw new FileStorageException("Could not store profile picture. Please try again!");
        }
    }

    public enum UploadType {
        USER, COURSE, ASSIGNMENT, EVENT
    }
}

