package spring.summer.socialnetwork.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import spring.summer.socialnetwork.models.FileData;
import spring.summer.socialnetwork.models.ImageData;
import spring.summer.socialnetwork.repositories.FileDataRepository;
import spring.summer.socialnetwork.repositories.StorageRepository;
import spring.summer.socialnetwork.repositories.UserRepository;
import spring.summer.socialnetwork.util.ImageUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@Service
public class StorageService {

    @Autowired
    private StorageRepository repository;
    @Autowired
    private FileDataRepository fileDataRepository;

    @Autowired
    private UserRepository userRepository;

    private final String FOLDER_PATH="C:\\Users\\damian\\Pictures\\springimages\\";

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


    public String uploadImageToFileSystem(MultipartFile file) throws IOException {
        String filePath=FOLDER_PATH+file.getOriginalFilename();

        FileData fileData=fileDataRepository.save(FileData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .filePath(filePath).build());

        file.transferTo(new File(filePath));

        if (fileData != null) {
            return "file uploaded successfully : " + filePath;
        }
        return null;
    }

    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
        Optional<FileData> fileData = fileDataRepository.findByName(fileName);
        String filePath=fileData.get().getFilePath();
        byte[] images = Files.readAllBytes(new File(filePath).toPath());
        return images;
    }

    public byte[] downloadImageForUser(Long userId) throws IOException {
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


    public String uploadImageToFileSystemForUser(MultipartFile file, Long userId) throws IOException {
        var user = userRepository.getUserById(userId);
        if(!user.isPresent())
        {
            throw new IOException();
        }
        String filePath=FOLDER_PATH+file.getOriginalFilename();

        FileData fileData=fileDataRepository.save(FileData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .user(user.get())
                .filePath(filePath).build());// pathem powinno być id lub generowana unikatowa wartość

        var test1 = fileDataRepository.findByUser(user.get());
        if(!test1.isPresent()){
            throw new IOException();
        }
        System.out.println("wyswietl typ: "+test1.get().getType());
        System.out.println("wyswietl typ: "+test1.get().getName());

        var myStr = test1.get().getName();
        var nStr = myStr.substring(myStr.indexOf("."));
        //System.out.println("rozszerzenie: "+nStr);


        test1.get().setFilePath(String.valueOf(test1.get().getId())+nStr);
        var test2 = fileDataRepository.save(test1.get());

        filePath=FOLDER_PATH+test2.getFilePath();

        file.transferTo(new File(filePath));

        if (fileData != null) {
            return "file uploaded successfully : " + filePath;
        }
        return null;
    }

}