package com.dipl.neuralphotos.service.nontransactional;

import com.dipl.neuralphotos.service.UploadService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

@Service
public class UploadServiceImpl implements UploadService {

    @Override
    public String uploadFile(MultipartFile file){
        String filename = file.getOriginalFilename();
        if (!file.isEmpty()) {
            File uploadedFile = new File("images/"+filename);
            if(uploadedFile.exists()){
                uploadedFile = new File("images/"+"1-"+filename);
            }
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(uploadedFile));
                stream.write(bytes);
                stream.close();
                return "Вы удачно загрузили " + filename + " в " + uploadedFile.getAbsolutePath() + "!";
            } catch (Exception e) {
                return "Вам не удалось загрузить " + filename + " => " + e.getMessage();
            }
        } else {
            return "Вам не удалось загрузить " + filename + " потому что файл пустой.";
        }
    }

}
