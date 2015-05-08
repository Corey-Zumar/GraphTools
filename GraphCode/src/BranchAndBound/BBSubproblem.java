package BranchAndBound;

import Graph.ColoredVertex;
import Graph.GraphEdge;
import Graph.NPGraph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Corey on 5/6/15.
 */
public class BBSubproblem implements Comparable<BBSubproblem> {

    public Set<ColoredVertex> validVertices;
    public ColoredVertex startVertex, targetVertex;

    public int currentCost;

    public List<ColoredVertex> path;

    public BBSubproblem(ColoredVertex startVertex,
                        Set<ColoredVertex> validVertices, ColoredVertex targetVertex, int cost) {
        this.validVertices = validVertices;
        this.startVertex = startVertex;
        this.targetVertex = targetVertex;
        this.currentCost = cost;

        path = new ArrayList<ColoredVertex>();
        path.add(startVertex);
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

        path = new ArrayList<ColoredVertex>(oldProblem.path);
        path.add(newVertex);
    }

    public boolean isValid() {
        int size = path.size();

        if(size < 4) {
            return true;
        }

        ColoredVertex last = path.get(size - 1);
        ColoredVertex secondToLast = path.get(size - 2);
        ColoredVertex thirdToLast = path.get(size - 3);
        ColoredVertex fourthToLast = path.get(size - 4);
        return !((last.color == secondToLast.color) && (secondToLast.color == thirdToLast.color) &&
                (thirdToLast.color == fourthToLast.color));
    }

    @Override
    public int compareTo(BBSubproblem other) {
        return currentCost - other.currentCost;
    }
}
