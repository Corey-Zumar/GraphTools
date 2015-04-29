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


}
