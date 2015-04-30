import org.jgrapht.graph.DefaultWeightedEdge;

/**
 * Created by Corey on 4/28/15.
 */

/**
 * In case we want custom edge attributes
 */
public class GraphEdge extends DefaultWeightedEdge {

    public GraphEdge() {

    }

    public String toString() {
        return String.valueOf((int) getWeight());
    }

    public int getSourceVertex() {
        return ((ColoredVertex) getSource()).number;
    }

    public int getTargetVertex() {
        return ((ColoredVertex) getTarget()).number;
    }


}
