package Graph;

import org.jgrapht.graph.DefaultWeightedEdge;

/**
 * Created by Corey on 4/28/15.
 */

/**
 * In case we want custom edge attributes
 */
public class GraphEdge extends DefaultWeightedEdge {

    public int customWeight = -1;

    public GraphEdge() {

    }

    public GraphEdge(int weight) {
        customWeight = weight;
    }

    public int getEdgeWeight() {
        return (customWeight == -1 ? (int) getWeight() : customWeight);
    }

    public String toString() {
        return String.valueOf(customWeight == -1 ? (int) getWeight() : customWeight);
    }

    public int getSourceVertex() {
        return ((ColoredVertex) getSource()).number;
    }

    public int getTargetVertex() {
        return ((ColoredVertex) getTarget()).number;
    }

    public ColoredVertex getSourceVertexObject() {
        return (ColoredVertex) getSource();
    }

    public ColoredVertex getTargetVertexObject() {
        return (ColoredVertex) getTarget();
    }


}
