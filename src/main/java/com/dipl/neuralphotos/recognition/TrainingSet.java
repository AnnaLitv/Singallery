package com.dipl.neuralphotos.recognition;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.imgrec.ColorMode;
import org.neuroph.imgrec.FractionRgbData;
import org.neuroph.imgrec.ImageRecognitionHelper;
import org.neuroph.imgrec.image.Dimension;
import org.neuroph.nnet.learning.MomentumBackpropagation;
import org.neuroph.util.TransferFunctionType;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TrainingSet {
    private List<String> labels;
    private NeuralNetwork nnet;

    public void trainSet(String path) throws IOException {
        Map<String,FractionRgbData> map = ImageRecognitionHelper.getFractionRgbDataForDirectory (new File(path), new Dimension(20, 20));
        getLabels(map);
        DataSet dataSet = ImageRecognitionHelper.createRGBTrainingSet(labels, map);
        train(dataSet);
    }

    private void getLabels(Map<String,FractionRgbData> map){
        labels = new ArrayList<String>();
        for (Map.Entry<String,FractionRgbData> entry:map.entrySet()){
            labels.add(entry.getKey());
        }
    }

    private void train(DataSet dataSet){
        // create neural network
        List<Integer> hiddenLayers = new ArrayList<Integer>();
        hiddenLayers.add(12);
        nnet = ImageRecognitionHelper.createNewNeuralNetwork("someNetworkName",
                new Dimension(20,20), ColorMode.COLOR_RGB, labels, hiddenLayers, TransferFunctionType.SIGMOID);

        // set learning rule parameters
        MomentumBackpropagation mb = (MomentumBackpropagation)nnet.getLearningRule();
        mb.setLearningRate(0.05);
        mb.setMaxError(0.001);
        mb.setMomentum(0.9);

        // traiin network
        System.out.println("NNet start learning...");
        nnet.learn(dataSet);
        System.out.println("NNet learned");
    }

    public NeuralNetwork getNnet() {
        return nnet;
    }
}
