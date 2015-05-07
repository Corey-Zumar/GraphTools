package Graph;

import org.jgrapht.VertexFactory;

/**
 * Created by Corey on 4/28/15.
 */
public class ColoredVertexFactory implements VertexFactory {

    @Override
    public ColoredVertex createVertex() {
        return new ColoredVertex();
    }
}
