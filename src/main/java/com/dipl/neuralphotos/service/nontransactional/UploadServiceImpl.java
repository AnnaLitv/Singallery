package com.dipl.neuralphotos.service.nontransactional;

import com.dipl.neuralphotos.recognition.Recognition;
import com.dipl.neuralphotos.recognition.TrainingSet;
import com.dipl.neuralphotos.service.UploadService;
import org.neuroph.core.NeuralNetwork;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Handler;

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
                if(checkToDouble(uploadedFile)){
                    trainSet();
                    return "Вы удачно загрузили " + filename + " в " + uploadedFile.getAbsolutePath() + "!";
                }else {
                    uploadedFile.delete();
                    throw new RuntimeException("Find duplicate!!!");
                }
            } catch (Exception e) {
                return "Вам не удалось загрузить " + filename + " => " + e.getMessage();
            }
        } else {
            return "Вам не удалось загрузить " + filename + " потому что файл пустой.";
        }
    }

    private void trainSet() throws IOException {
        TrainingSet set = new TrainingSet();
        set.trainSet("images/");
    }

    private boolean checkToDouble(File fileToRecognize) throws IOException {
        Recognition recognition = new Recognition();
        NeuralNetwork neuralNetwork = NeuralNetwork.createFromFile("nnet/singallery.nnet");
        HashMap<String,Double> resultMap = recognition.recognize(neuralNetwork,fileToRecognize);
        Map.Entry<String,Double> maxEntry = null;
        for (Map.Entry<String,Double> entry : resultMap.entrySet())
        {
            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0)
            {
                maxEntry = entry;
            }
        }
        return maxEntry == null || !(maxEntry.getValue() > 0.5);
    }

}
