package com.dipl.neuralphotos.recognition;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.exceptions.VectorSizeMismatchException;
import org.neuroph.imgrec.ImageRecognitionPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Recognition {

    public HashMap<String,Double> recognize(NeuralNetwork nnet, File fileToRecognize) throws IOException {
        ImageRecognitionPlugin imageRecognition = (ImageRecognitionPlugin)nnet.getPlugin(ImageRecognitionPlugin.class);
        try {
            HashMap<String, Double> output = imageRecognition.recognizeImage(fileToRecognize); // specify some existing image file here
            System.out.println(output.toString());
            return output;
        } catch(IOException ioe) {
            System.out.println("Error: could not read file!");
            return null;

        } catch (VectorSizeMismatchException vsme) {
            System.out.println("Error: Image dimensions dont !");
            return null;
        }
    }
}
