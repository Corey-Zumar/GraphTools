package Graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Corey on 5/5/15.
 */

/**
 * Pretty straightforward
 */
public class InstanceProcessor {

    public static NPGraph createGraphFromInstance(String fileName) throws IOException {
        List<String> lineData = parseFileDataByLine(fileName);
        int numVertices = Integer.valueOf(lineData.remove(0).trim());
        String graphColoring = lineData.remove(lineData.size() - 1);

        NPGraph<ColoredVertex,GraphEdge> instanceGraph = new NPGraph<ColoredVertex,GraphEdge>(GraphEdge.class);
        for(int i = 0; i < numVertices; i++) {
            ColoredVertex vertex = new ColoredVertex(graphColoring.charAt(i) == ColoredVertex.COLOR_BLUE_READABLE ?
                    ColoredVertex.COLOR_BLUE : ColoredVertex.COLOR_RED);
            vertex.setNumber(i);
            instanceGraph.addVertex(vertex);
        }

        for(int i = 0; i < lineData.size(); i++) {
            String adjacencyLine = lineData.get(i);
            String[] edgeWeights = adjacencyLine.split("\\s+");
            List<Integer> weights = new ArrayList<Integer>();
            for(String edgeWeight : edgeWeights) {
                try {
                    weights.add(Integer.valueOf(edgeWeight.trim()));
                } catch(Exception e) {
                    continue;
                }
            }
            for(int j = 0; j < weights.size(); j++) {
                int weight = Integer.valueOf(weights.get(j));
                GraphEdge newEdge = new GraphEdge(weight);
                if(i != j) {
                    instanceGraph.addEdge(instanceGraph.getVertex(i), instanceGraph.getVertex(j), newEdge);
                }
            }
        }
        return instanceGraph;
    }

    private static List<String> parseFileDataByLine(String fileName) throws IOException {
        List<String> lineData = new ArrayList<String>();
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                lineData.add(line);
                line = br.readLine();
            }
        } finally {
            br.close();
        }
        return lineData;
    }

    public static List<Integer> getInstanceSizes(String directory) {
        int fileNumber = 1;
        List<Integer> sizes = new ArrayList<Integer>();
        try {
            while (true) {
                String fileName = fileNumber + ".in";
                String path = directory + fileName;

                BufferedReader br = new BufferedReader(new FileReader(path));
                sizes.add(Integer.valueOf(br.readLine().trim()));
                fileNumber++;
            }
        } catch(IOException e) {
            return sizes;
        }
    }


}
