import org.jgrapht.graph.DefaultWeightedEdge;

/**
 * Created by Corey on 4/28/15.
 */

/**
 * In case we want custom edge attributes
 */
public class GraphEdge extends DefaultWeightedEdge {

    int customWeight = -1;

    public GraphEdge() {

    }

    public GraphEdge(int weight) {
        customWeight = weight;
    }

    public int getEdgeWeight() {
        return (int) getWeight();
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


}
