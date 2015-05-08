package BranchAndBound;

import Graph.ColoredVertex;

import java.io.*;

/**
 * Created by Corey on 5/6/15.
 */
public class Solution {

    private String fileName;
    private BBSubproblem bestSolution;

    public Solution(String fileName, BBSubproblem bestSolution) {
        this.fileName = fileName;
        this.bestSolution = bestSolution;
    }

    public void writeToFile() throws IOException {
        String outputFileName = fileName.substring(0,fileName.indexOf(".")) + ".out";
        StringBuilder outputStringBuilder = new StringBuilder();
        for(ColoredVertex vertex : bestSolution.path) {
            outputStringBuilder.append((vertex.number + 1) + " ");
        }
        String outputString = outputStringBuilder.toString();
        outputString = outputString.substring(0, outputString.length() - 1);

        Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("../outputs/" + outputFileName), "utf-8"));
        writer.write(outputString + "\n");
        writer.close();
    }

}
