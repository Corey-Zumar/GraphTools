package BranchAndBound;

import Graph.ColoredVertex;
import Graph.GraphEdge;
import Graph.NPGraph;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Corey on 5/6/15.
 */
public class BBSubproblem {

    Set<ColoredVertex> validVertices;
    ColoredVertex startVertex, targetVertex;

    int currentCost;

    public BBSubproblem(ColoredVertex startVertex,
                        Set<ColoredVertex> validVertices, ColoredVertex targetVertex, int cost) {
        this.validVertices = validVertices;
        this.startVertex = startVertex;
        this.targetVertex = targetVertex;
        this.currentCost = cost;
    }

    public BBSubproblem(NPGraph graph, BBSubproblem oldProblem, ColoredVertex newVertex) {
        this.validVertices = new HashSet<ColoredVertex>(oldProblem.validVertices);
        this.validVertices.add(newVertex);
        this.startVertex = oldProblem.startVertex;
        this.targetVertex = newVertex;

        int newEdgeWeight = ((GraphEdge) graph.getEdge(oldProblem.targetVertex, newVertex)).getEdgeWeight();
        if(oldProblem.currentCost < Integer.MAX_VALUE) {
            this.currentCost = oldProblem.currentCost + newEdgeWeight;
        } else {
            this.currentCost = newEdgeWeight;
        }
    }


}
