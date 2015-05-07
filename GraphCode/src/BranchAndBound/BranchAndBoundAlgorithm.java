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

    public static void branchAndBound(NPGraph inputGraph) {
        int randomStartPoint = new Random().ints(1,0,inputGraph.vertexSet().size()).findFirst().getAsInt();
        ColoredVertex startVertex = inputGraph.getVertex(randomStartPoint);
        HashSet<ColoredVertex> validSet = new HashSet<ColoredVertex>();
        validSet.add(startVertex);

        BBSubproblem firstProblem = new BBSubproblem(startVertex, validSet, startVertex, Integer.MAX_VALUE);

        Queue<BBSubproblem> problemQueue = new PriorityQueue<BBSubproblem>();
        problemQueue.add(firstProblem);
        int bestSoFar = Integer.MAX_VALUE;
        while(!problemQueue.isEmpty()) {
            BBSubproblem subproblem = problemQueue.remove();
            Set<ColoredVertex> complement = new HashSet<ColoredVertex>(inputGraph.vertexSet());
            complement.removeAll(subproblem.validVertices);

            for(ColoredVertex vertex : complement) {
                BBSubproblem newProblem = new BBSubproblem(inputGraph, subproblem, vertex);



                if(computeLowerBound(inputGraph, newProblem) < bestSoFar) {
                    
                }
            }
        }
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

        return lightestEdgeFromA + lightestEdgeFromB + complementMSTWeight;
    }





}
