package BranchAndBound;

import Graph.ColoredVertex;
import Graph.GraphEdge;
import Graph.NPGraph;
import org.jgrapht.alg.KruskalMinimumSpanningTree;
import org.jgrapht.graph.Subgraph;

import java.util.*;

/**
 * Created by Corey on 5/6/15.
 */
public class BranchAndBoundAlgorithm {


    public static BBSubproblem branchAndBoundRandomStart(NPGraph inputGraph) {
        int randomStartPoint = new Random().ints(1,0,inputGraph.vertexSet().size()).findFirst().getAsInt();
        return branchAndBound(inputGraph, randomStartPoint);
    }

    public static BBSubproblem branchAndBound(NPGraph inputGraph, int startPoint) {
        ColoredVertex startVertex = inputGraph.getVertex(startPoint);
        HashSet<ColoredVertex> validSet = new HashSet<ColoredVertex>();
        validSet.add(startVertex);

        BBSubproblem firstProblem = new BBSubproblem(startVertex, validSet, startVertex, Integer.MAX_VALUE);

        Stack<BBSubproblem> problemQueue = new Stack<BBSubproblem>();
        problemQueue.add(firstProblem);
        BBSubproblem bestSoFar = firstProblem;

        while(!problemQueue.isEmpty()) {
            BBSubproblem subproblem = problemQueue.pop();
            Set<ColoredVertex> complement = new HashSet<ColoredVertex>(inputGraph.vertexSet());
            complement.removeAll(subproblem.validVertices);

            for(ColoredVertex vertex : complement) {
                BBSubproblem newProblem = new BBSubproblem(inputGraph, subproblem, vertex);

                if(!newProblem.isValid()) {
                    continue;
                }

                if(isCompleteSolution(inputGraph, newProblem) && newProblem.compareTo(bestSoFar) < 0) {
                    bestSoFar = newProblem;
                } else if(computeLowerBound(inputGraph, newProblem) < bestSoFar.currentCost) {
                    problemQueue.push(newProblem);
                }
            }
        }
        return bestSoFar;
    }

    private static boolean isCompleteSolution(NPGraph inputGraph, BBSubproblem subproblem) {
        return subproblem.validVertices.size() == inputGraph.vertexSet().size();
    }

    /** THE NAIVE LOWER BOUND
     *
     * @param inputGraph
     * @param subproblem
     * @return
     */
    private static int computeLowerBound(NPGraph inputGraph, BBSubproblem subproblem) {
        Set<ColoredVertex> complement = new HashSet<ColoredVertex>(inputGraph.vertexSet());
        complement.removeAll(subproblem.validVertices);
        int lightestEdgeFromA = Integer.MAX_VALUE;
        int lightestEdgeFromB = Integer.MAX_VALUE;
        for(ColoredVertex vertex : complement) {
            int edgeWeightA = ((GraphEdge) inputGraph.getEdge(subproblem.startVertex, vertex)).getEdgeWeight();
            if(edgeWeightA < lightestEdgeFromA) {
                lightestEdgeFromA = edgeWeightA;
            }

            int edgeWeightB = ((GraphEdge) inputGraph.getEdge(subproblem.targetVertex, vertex)).getEdgeWeight();
            if(edgeWeightB < lightestEdgeFromB) {
                lightestEdgeFromB = edgeWeightB;
            }
        }

        Subgraph<ColoredVertex, GraphEdge, NPGraph<ColoredVertex,GraphEdge>> complementGraph =
                new Subgraph<ColoredVertex, GraphEdge, NPGraph<ColoredVertex, GraphEdge>>(inputGraph, complement);

        KruskalMinimumSpanningTree<ColoredVertex,GraphEdge> MSTInstance
                = new KruskalMinimumSpanningTree<ColoredVertex, GraphEdge>(complementGraph);

        int complementMSTWeight = (int) MSTInstance.getMinimumSpanningTreeTotalWeight();

        return subproblem.currentCost + lightestEdgeFromA + lightestEdgeFromB + complementMSTWeight;
    }





}
