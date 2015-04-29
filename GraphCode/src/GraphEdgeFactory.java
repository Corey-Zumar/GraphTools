import org.jgrapht.EdgeFactory;

/**
 * Created by Corey on 4/28/15.
 */
public class GraphEdgeFactory implements EdgeFactory<ColoredVertex, GraphEdge> {

    @Override
    public GraphEdge createEdge(ColoredVertex v1, ColoredVertex v2) {
        return new GraphEdge();
    }
}
