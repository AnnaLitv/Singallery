package com.dipl.neuralphotos.recognition;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.exceptions.VectorSizeMismatchException;
import org.neuroph.imgrec.ImageRecognitionPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Recognition {

    public void recognize(NeuralNetwork nnet) throws IOException {
        ImageRecognitionPlugin imageRecognition = (ImageRecognitionPlugin)nnet.getPlugin(ImageRecognitionPlugin.class);

        try {
            HashMap<String, Double> output = imageRecognition.recognizeImage(new File("images/image4.jpg")); // specify some existing image file here
            System.out.println(output.toString());
        } catch(IOException ioe) {
            System.out.println("Error: could not read file!");
        } catch (VectorSizeMismatchException vsme) {
            System.out.println("Error: Image dimensions dont !");
        }
    }
}
