import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraphView;
import org.jgrapht.ext.JGraphXAdapter;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Corey on 4/28/15.
 */
public class Main {


    public static void main(String[] args) {

        int minVertices = 4;
        int maxVertices = 4;
        int minEdgeWeight = 5;
        int maxEdgeWeight = 9;

        NPGraph<ColoredVertex,GraphEdge> randomGraph = GraphGenerator.generateRandomGraph(minVertices, maxVertices, minEdgeWeight, maxEdgeWeight);
        randomGraph.toString();
        JGraphXAdapter<ColoredVertex, GraphEdge> adapter = new JGraphXAdapter<ColoredVertex, GraphEdge>(randomGraph);

        JFrame frame = new JFrame("NP Graph with " + randomGraph.vertexSet().size() + " vertices");
        frame.setPreferredSize(new Dimension(1000, 1000));

        mxGraphComponent component = new mxGraphComponent(adapter);

        new mxCircleLayout(adapter).execute(adapter.getDefaultParent());

        frame.add(component);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);

        mxGraphView view = component.getGraph().getView();
        int compLen = component.getWidth();
        int viewLen = (int)view.getGraphBounds().getWidth();
        view.setScale((double) compLen / viewLen * view.getScale());
    }



}
