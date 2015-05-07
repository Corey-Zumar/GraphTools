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
        int graphSize = inputGraph.vertexSet().size();
        int totalWeight = 0;
        for (int i = 1; i < graphSize; i++) { //We have to run this 49 times in order to find a successor for each vertex
            int bestEdgeValue = Integer.MAX_VALUE;
            ColoredVertex newVertex = new ColoredVertex();
            int newVertNo = 0;
            for (int j = 1; j < graphSize; j++) { //For each vertex, we check every other vertex
                if (j != lastVertNo && !visited.contains(vertexList.get(j))) {
                    GraphEdge newEdge = (GraphEdge) inputGraph.getEdge(lastVertex, vertexList.get(j));
                    if (bestEdgeValue > newEdge.getEdgeWeight()) {
                        newVertex = vertexList.get(j);
                        newVertNo = j;
                        bestEdgeValue = newEdge.getEdgeWeight();
                    }
                }
            }
            visited.add(newVertex);
            lastVertex = newVertex;
            lastVertNo = newVertNo;
            totalWeight += bestEdgeValue;
            visitedByNo.add(newVertNo);
        }

        int bluesSeen = 0;
        int redsSeen = 0;
        for (int k = graphSize - 1; k >= 0; k--) {
            if (visited.get(k).color == 0) {
                bluesSeen++;
                redsSeen = 0;
            }

            if (visited.get(k).color == 1) {
                redsSeen++;
                bluesSeen = 0;
            }


            if (bluesSeen == 4) {
                int bestFix = Integer.MAX_VALUE;
                ColoredVertex bestVertFix = new ColoredVertex();
                int bestVertFixNo = 50;
                int whereInVisited = 50;
                ColoredVertex movedVertex = visited.get(k + 1);
                int movedVertexNo = visitedByNo.get(k + 1);

                for (int l = k - 1; l >= 0; l--) {
                    ColoredVertex currVert = visited.get(l);
                    int currVertNo = visitedByNo.get(l);
                    if (currVert.color == 1) {
                        GraphEdge edgeOne = (GraphEdge) inputGraph.getEdge(visited.get(k + 2), currVert);
                        GraphEdge edgeTwo = (GraphEdge) inputGraph.getEdge(visited.get(k), currVert);
                        if (edgeOne.getEdgeWeight() + edgeTwo.getEdgeWeight() < bestFix) {
                            bestFix = edgeOne.getEdgeWeight() + edgeTwo.getEdgeWeight();
                            bestVertFix = currVert;
                            bestVertFixNo = currVertNo;
                            whereInVisited = l;
                        }
                    }
                }

                visited.set(k + 1, bestVertFix);
                visitedByNo.set(k + 1, bestVertFixNo);
                visited.set(whereInVisited, movedVertex);
                visitedByNo.set(whereInVisited, movedVertexNo);

                bluesSeen = 1;
            }

            if (redsSeen == 4) {

                int bestFix = Integer.MAX_VALUE;
                ColoredVertex bestVertFix = new ColoredVertex();
                int bestVertFixNo = 50;
                int whereInVisited = 50;
                ColoredVertex movedVertex = visited.get(k + 1);
                int movedVertexNo = visitedByNo.get(k + 1);

                for (int l = k - 1; l >= 0; l--) {
                    ColoredVertex currVert = visited.get(l);
                    int currVertNo = visitedByNo.get(l);
                    if (currVert.color == 0) {
                        GraphEdge edgeOne = (GraphEdge) inputGraph.getEdge(visited.get(k + 2), currVert);
                        GraphEdge edgeTwo = (GraphEdge) inputGraph.getEdge(visited.get(k), currVert);
                        if (edgeOne.getEdgeWeight() + edgeTwo.getEdgeWeight() < bestFix) {
                            bestFix = edgeOne.getEdgeWeight() + edgeTwo.getEdgeWeight();
                            bestVertFix = currVert;
                            bestVertFixNo = currVertNo;
                            whereInVisited = l;
                        }
                    }
                }

                visited.set(k + 1, bestVertFix);
                visitedByNo.set(k + 1, bestVertFixNo);
                visited.set(whereInVisited, movedVertex);
                visitedByNo.set(whereInVisited, movedVertexNo);

                redsSeen = 1;
            }
        }

        totalWeight = 0;
        for (int i = 0; i < visited.size() - 1; i++) {
            GraphEdge currEdge = (GraphEdge) inputGraph.getEdge(visited.get(i), visited.get(i + 1));
            totalWeight += currEdge.getEdgeWeight();
        }

    }
}
