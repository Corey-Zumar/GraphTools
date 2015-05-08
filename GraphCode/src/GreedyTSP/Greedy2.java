package GreedyTSP;

import Graph.ColoredVertex;
import Graph.GraphEdge;
import Graph.NPGraph;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by Corey on 5/8/15.
 */
public class Greedy2 {

    public static Path executeGreedyAlgorithmRandomStart(NPGraph<ColoredVertex, GraphEdge> inputGraph) {
        int randomVertex = new Random().ints(1, 0, inputGraph.vertexSet().size()).findFirst().getAsInt();
        return executeGreedyAlgorithm(inputGraph, randomVertex);
    }

    public static Path executeGreedyAlgorithm(NPGraph<ColoredVertex, GraphEdge> inputGraph, int startVertex) {
        Path greedyPath = new Path(inputGraph.getVertex(startVertex));

        int verticesRemaining = inputGraph.vertexSet().size() - greedyPath.size();

        while (verticesRemaining > 0) {

            Set<ColoredVertex> complement = new HashSet<ColoredVertex>(inputGraph.vertexSet());
            complement.removeAll(greedyPath.vertices);

            int minDist = Integer.MAX_VALUE;
            ColoredVertex minVertex = null;


            for (ColoredVertex vertex : complement) {
                GraphEdge edge = inputGraph.getEdge(greedyPath.getMostRecentVertex(), vertex);
                if (edge.getEdgeWeight() < minDist) {

                    if (greedyPath.lastThreeSameColor() || greedyPath.lastTwoSameColor() && verticesRemaining < 4) {
                        if (greedyPath.getMostRecentVertex().color != vertex.color) {
                            minDist = edge.getEdgeWeight();
                            minVertex = vertex;
                        }
                    } else {
                        minDist = edge.getEdgeWeight();
                        minVertex = vertex;
                    }
                }
            }

            verticesRemaining--;

            greedyPath.addVertex(minVertex);
        }

        return greedyPath;
    }


}
