package Graph;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Corey on 5/8/15.
 */
public class OutputFileWriter {

    public static void writeOutputsToMergedFile(String outputFileName) throws IOException {
        int fileCount = 0;
        File instanceFolder = new File("../instances/");
        for(String name : instanceFolder.list()) {
            if(name.contains(".in")) {
                fileCount++;
            }
        }

        File outputsFolder = new File("../outputs/");
        List<String> outputFileNames = new ArrayList<String>();
        for(String fileName : outputsFolder.list()) {
            outputFileNames.add(fileName);
        }

        StringBuilder outputBuilder = new StringBuilder();
        for(int i = 1; i <= fileCount; i++) {
            String fileName = i + ".out";
            try {
                outputBuilder.append(getFirstLineOfOutputFile(fileName) + "\n");
            } catch (IOException e) {
                outputBuilder.append("\n");
            }
        }

        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFileName + ".out"), "utf-8"));
        writer.write(outputBuilder.toString());
        System.out.println(outputBuilder.toString());
        writer.close();

    }

    private static String getFirstLineOfOutputFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("../outputs/" + fileName));
        try {
            return br.readLine();
        } finally {
            br.close();
        }
    }


}
