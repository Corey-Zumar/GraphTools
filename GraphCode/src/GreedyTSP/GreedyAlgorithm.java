package GreedyTSP;

import Graph.*;
import java.util.*;

/**
 * Created by jongyoonlee on 5/6/15.
 */
public class GreedyAlgorithm {

    public static void greedy(NPGraph inputGraph) {
        List<ColoredVertex> vertexList = inputGraph.getVerticesAsList();
        List<ColoredVertex> visited = new ArrayList<ColoredVertex>();
        ColoredVertex lastVertex = vertexList.get(0);
        int lastVertNo = 0;
        visited.add(lastVertex);
        List<Integer> visitedByNo = new ArrayList<Integer>();
        int totalWeight = 0;
        for (int i = 1; i < 50; i++) { //We have to run this 49 times in order to find a successor for each vertex
            int bestEdgeValue = Integer.MAX_VALUE;
            ColoredVertex newVertex = new ColoredVertex();
            int newVertNo = 0;
            int newEdgeWeight = 0;
            for (int j = 1; j < 50; j++) { //For each vertex, we check every other vertex
                if (j != lastVertNo && !visited.contains(vertexList.get(j))) {
                    GraphEdge newEdge = (GraphEdge) inputGraph.getEdge(lastVertex, vertexList.get(j));
                    if (bestEdgeValue > newEdge.getEdgeWeight()) {
                        newVertex = vertexList.get(j);
                        newVertNo = j;
                        newEdgeWeight = newEdge.getEdgeWeight();
                    }
                }
            }
            visited.add(newVertex);
            lastVertex = newVertex;
            lastVertNo = newVertNo;
            totalWeight += newEdgeWeight;
            visitedByNo.add(newVertNo);
        }
        System.out.println(visitedByNo);
        System.out.print(totalWeight);
    }
}
