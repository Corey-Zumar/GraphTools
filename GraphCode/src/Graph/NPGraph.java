package Graph;

import org.jgrapht.ext.MatrixExporter;
import org.jgrapht.graph.ListenableUndirectedWeightedGraph;

import java.io.*;
import java.util.*;

/**
 * Created by Corey on 4/30/15.
 */
public class NPGraph<V, E> extends ListenableUndirectedWeightedGraph<V, E> {

    private List<ColoredVertex> verticesList;

    public NPGraph(Class edgeClass) {
        super(edgeClass);
    }

    public void outputGraphData() throws FileNotFoundException, UnsupportedEncodingException {
        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("graph.txt"), "utf-8"));
        new MatrixExporter<ColoredVertex, GraphEdge>().exportAdjacencyMatrix(writer, (ListenableUndirectedWeightedGraph<ColoredVertex, GraphEdge>) this);
    }

    public String toString() {

        Map<Integer, List<GraphEdge>> adjacencyMap = new TreeMap<Integer, List<GraphEdge>>();
        for (E edge : edgeSet()) {
            int source = ((GraphEdge) edge).getSourceVertex();
            int target = ((GraphEdge) edge).getTargetVertex();
            if (adjacencyMap.containsKey(source)) {
                adjacencyMap.get(source).add((GraphEdge) edge);
            } else {
                List<GraphEdge> vertexConnections = new ArrayList<GraphEdge>();
                vertexConnections.add((GraphEdge) edge);
                adjacencyMap.put(source, vertexConnections);
            }
            if (adjacencyMap.containsKey(target)) {
                adjacencyMap.get(target).add((GraphEdge) edge);
            } else {
                List<GraphEdge> vertexConnections = new ArrayList<GraphEdge>();
                vertexConnections.add((GraphEdge) edge);
                adjacencyMap.put(target, vertexConnections);
            }

        }
        StringBuilder outputBuilder = new StringBuilder();
        outputBuilder.append(String.valueOf(adjacencyMap.size()));
        outputBuilder.append("\n");
        for (Map.Entry<Integer, List<GraphEdge>> entry : adjacencyMap.entrySet()) {
            entry.getValue().add(entry.getKey(), new GraphEdge(0));
            StringBuilder lineBuilder = new StringBuilder();
            for (GraphEdge edge : entry.getValue()) {
                lineBuilder.append(edge.toString() + " ");
            }
            String line = lineBuilder.toString();
            outputBuilder.append(line.substring(0, line.length() - 1) + "\n");
        }

        StringBuilder colorBuilder = new StringBuilder();
        for (V vertex : vertexSet()) {
            colorBuilder.append(((ColoredVertex) vertex).color ==
                    ColoredVertex.COLOR_BLUE ? ColoredVertex.COLOR_BLUE_READABLE : ColoredVertex.COLOR_RED_READABLE);
        }
        outputBuilder.append(colorBuilder.toString());

        return outputBuilder.toString();
    }

    public List<ColoredVertex> getVerticesAsList() {
        if (verticesList == null) {
            verticesList = new ArrayList<ColoredVertex>();
            for (V vertex : vertexSet()) {
                verticesList.add((ColoredVertex) vertex);
            }
        }
        return verticesList;
    }

    public ColoredVertex getVertex(int number) {
        return getVerticesAsList().get(number);
    }

}
