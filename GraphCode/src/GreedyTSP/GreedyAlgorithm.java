package GreedyTSP;

import Graph.*;
import java.util.*;

/**
 * Created by jongyoonlee on 5/6/15.
 */
public class GreedyAlgorithm {

    public static List<ColoredVertex> greedy(NPGraph inputGraph) {
        List<ColoredVertex> vertexList = inputGraph.getVerticesAsList();
        List<ColoredVertex> visited = new ArrayList<ColoredVertex>();
        ColoredVertex lastVertex = vertexList.get(0);
        int lastVertNo = 0;
        visited.add(lastVertex);
        List<Integer> visitedByNo = new ArrayList<Integer>();
        visitedByNo.add(lastVertNo);
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


            if (bluesSeen >= 4) {
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

                String interColor = new String();
                for (int i = 0; i < visited.size() - 1; i++) {
                    GraphEdge thisEdge = (GraphEdge) inputGraph.getEdge(visited.get(i), visited.get(i + 1));
                    totalWeight += thisEdge.getEdgeWeight();
                    interColor += visited.get(i).color;
                }
                interColor += visited.get(visited.size() - 1).color;
                //System.out.println(interColor);

                if (whereInVisited != 50) {
                    visited.set(k + 1, bestVertFix);
                    visitedByNo.set(k + 1, bestVertFixNo);
                    visited.set(whereInVisited, movedVertex);
                    visitedByNo.set(whereInVisited, movedVertexNo);
                    bluesSeen = 1;
                } else {
                    //System.out.println("BOO");
                    int consecSeen = 0;
                    int okFix = Integer.MAX_VALUE;
                    int fixIndex = 50;
                    for (int z = k + 4; z < graphSize; z++) {
                        if (visited.get(z).color == 1) {
                            consecSeen++;
                            if (consecSeen > 1) {
                                GraphEdge firstEdge = (GraphEdge) inputGraph.getEdge(visited.get(z - 1), k+1);
                                GraphEdge secondEdge = (GraphEdge) inputGraph.getEdge(visited.get(z), k+1);
                                if (firstEdge.getEdgeWeight() + secondEdge.getEdgeWeight() < okFix) {
                                    fixIndex = z;
                                }
                            }
                        } else {
                            consecSeen = 0;
                        }
                    }
                    ColoredVertex toRemove = visited.remove(k + 1);
                    int toRemoveNo = visitedByNo.remove(k + 1);
                    visited.add(fixIndex - 1, toRemove);
                    visitedByNo.add(fixIndex - 1, toRemoveNo);
                    bluesSeen = 3;
                }
            }

            if (redsSeen >= 4) {

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

                String interColor = new String();
                for (int i = 0; i < visited.size() - 1; i++) {
                    GraphEdge thisEdge = (GraphEdge) inputGraph.getEdge(visited.get(i), visited.get(i + 1));
                    totalWeight += thisEdge.getEdgeWeight();
                    interColor += visited.get(i).color;
                }
                interColor += visited.get(visited.size() - 1).color;
                //System.out.println(interColor);

                if (whereInVisited != 50) {
                    visited.set(k + 1, bestVertFix);
                    visitedByNo.set(k + 1, bestVertFixNo);
                    visited.set(whereInVisited, movedVertex);
                    visitedByNo.set(whereInVisited, movedVertexNo);
                    redsSeen = 1;
                } else {
                    int consecSeen = 0;
                    int okFix = Integer.MAX_VALUE;
                    int fixIndex = 50;
                    for (int z = k + 4; z < graphSize; z++) {
                        if (visited.get(z).color == 0) {
                            consecSeen++;
                            if (consecSeen > 1) {
                                GraphEdge firstEdge = (GraphEdge) inputGraph.getEdge(visited.get(z - 1), movedVertex);
                                GraphEdge secondEdge = (GraphEdge) inputGraph.getEdge(visited.get(z), movedVertex);
                                if (firstEdge.getEdgeWeight() + secondEdge.getEdgeWeight() < okFix) {
                                    fixIndex = z;
                                }
                            }
                        } else {
                            consecSeen = 0;
                        }
                    }
                    ColoredVertex toRemove = visited.remove(k + 1);
                    int toRemoveNo = visitedByNo.remove(k + 1);
                    visited.add(fixIndex - 1, toRemove);
                    visitedByNo.add(fixIndex - 1, toRemoveNo);
                    redsSeen = 3;
                }
            }
        }

        String answerColor = new String();
        totalWeight = 0;
        for (int i = 0; i < visited.size() - 1; i++) {
            GraphEdge currEdge = (GraphEdge) inputGraph.getEdge(visited.get(i), visited.get(i + 1));
            totalWeight += currEdge.getEdgeWeight();
            answerColor += visited.get(i).color;
        }
        answerColor += visited.get(visited.size() - 1).color;
        return visited;
        //System.out.println(visitedByNo);
        //System.out.println(totalWeight);
        //System.out.print(answerColor);

    }
}
