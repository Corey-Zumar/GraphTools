import org.jgrapht.ext.MatrixExporter;
import org.jgrapht.graph.ListenableUndirectedWeightedGraph;

import java.io.*;

/**
 * Created by Corey on 4/30/15.
 */
public class NPGraph<V,E> extends ListenableUndirectedWeightedGraph<V,E> {

    public NPGraph(Class edgeClass) {
        super(edgeClass);
    }

    public void outputGraphData() throws FileNotFoundException, UnsupportedEncodingException {
        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("graph.txt"), "utf-8"));
        new MatrixExporter<ColoredVertex, GraphEdge>().exportAdjacencyMatrix(writer, (ListenableUndirectedWeightedGraph<ColoredVertex,GraphEdge>) this);
    }

    public String toString() {

        for(V vertex : vertexSet()) {
            if(vertex instanceof  ColoredVertex) {
                ColoredVertex coloredVertex = (ColoredVertex) vertex;
                for(E edge : edgesOf(vertex)) {
                    /*if(((GraphEdge) edge).getSourceVertex() == 2) {
                        System.out.println(
                                ((GraphEdge) edge).getSourceVertex() + " | " +
                                        ((GraphEdge) edge).getTargetVertex() + " : " + edge.toString());
                    }*/

                }
            }
        }
        for(E edge : edgeSet()) {
                System.out.println(
                        ((GraphEdge) edge).getSourceVertex() + " | " +
                                ((GraphEdge) edge).getTargetVertex() + " : " + edge.toString());

        }

        return null;
    }

}
