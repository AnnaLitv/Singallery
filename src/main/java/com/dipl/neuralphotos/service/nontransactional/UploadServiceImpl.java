package com.dipl.neuralphotos.service.nontransactional;

import com.dipl.neuralphotos.recognition.Recognition;
import com.dipl.neuralphotos.recognition.TrainingSet;
import com.dipl.neuralphotos.service.UploadService;
import org.neuroph.core.NeuralNetwork;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Handler;

@Service
public class UploadServiceImpl implements UploadService {

    @Override
    public String uploadFile(MultipartFile file) throws IOException {
        String filename = file.getOriginalFilename();
        if (!file.isEmpty()) {
            File uploadedFile = new File("images/"+filename);
            if(uploadedFile.exists()){
                uploadedFile = new File("images/"+"1-"+filename);
            }
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(uploadedFile));
                stream.write(bytes);
                stream.close();
                if(checkToDouble(uploadedFile)){
                    trainSet();
                    return uploadedFile.getAbsolutePath() ;
                }else {
                    uploadedFile.delete();
                    throw new FileAlreadyExistsException("Find duplicate photo!");
                }
        } else {
            throw new FileNotFoundException("File "+filename+" is empty.");
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
        return maxEntry == null || !(maxEntry.getValue() > 0.7);
    }

}
