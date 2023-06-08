package spring.summer.socialnetwork.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import spring.summer.socialnetwork.models.FileData;
import spring.summer.socialnetwork.models.ImageData;
import spring.summer.socialnetwork.models.User;
import spring.summer.socialnetwork.repositories.FileDataRepository;
import spring.summer.socialnetwork.repositories.StorageRepository;
import spring.summer.socialnetwork.repositories.UserRepository;
import spring.summer.socialnetwork.util.ImageUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.util.Optional;

@Service
public class StorageService {

    @Autowired
    private StorageRepository repository;
    @Autowired
    private FileDataRepository fileDataRepository;

    @Autowired
    private UserRepository userRepository;

    private final String FOLDER_PATH=System.getProperty("user.dir\\");

    public String uploadImage(MultipartFile file) throws IOException {
        ImageData imageData = repository.save(ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes())).build());
        if (imageData != null) {
            return "file uploaded successfully : " + file.getOriginalFilename();
        }
        return null;
    }



    public byte[] downloadImage(String fileName) {
        Optional<ImageData> dbImageData = repository.findByName(fileName);
        byte[] images = ImageUtils.decompressImage(dbImageData.get().getImageData());
        return images;
    }





    public byte[] downloadImageForUser(Long userId) throws IOException {//jeszcze nie wykorzystywana funcjonalność - dla zdjęć profilowych w przyszłości
        var user = userRepository.getUserById(userId);
        if(!user.isPresent())
        {
            throw new IOException();
        }
        Optional<FileData> fileData = fileDataRepository.findByUser(user.get());
        String filePath=FOLDER_PATH+fileData.get().getFilePath();

        //System.out.println("|"+fileData.get().getFilePath()+"|");

        byte[] images = Files.readAllBytes(new File(filePath).toPath());
        return images;
    }



    public byte[] downloadProfileImage(Long userId) {
        var image = userRepository.findById(userId).get().getImage();
        return ImageUtils.decompressImage(image.getImageData());
        //throw new NoSuchFileException("Image does not exist");

    }

    public String uploadProfileImage(MultipartFile file, Long userId) throws IOException {
        ImageData imageData = repository.save(ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes())).build());
        var user = userRepository.getUserById(userId);
        if (imageData != null && user.isPresent()) {
            User userG = user.get();
            userG.setImage(imageData);
            userRepository.save(userG);
            return "file uploaded successfully : " + file.getOriginalFilename();
        }
        System.out.println("upload fail");
        return null;
    }
}