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
        int numVertices = Integer.valueOf(lineData.remove(0));
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
            String[] edgeWeights = adjacencyLine.split(" ");
            for(int j = 0; j < edgeWeights.length; j++) {
                int weight = Integer.valueOf(edgeWeights[j]);
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


}
