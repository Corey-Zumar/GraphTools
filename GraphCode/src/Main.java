import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraphView;
import org.jgrapht.ext.JGraphXAdapter;

import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 * Created by Corey on 4/28/15.
 */
public class Main {


    public static void main(String[] args) {
        try {
            processInstanceGraph();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void processInstanceGraph() throws IOException {
        NPGraph instanceGraph = InstanceProcessor.createGraphFromInstance("../instances/1.in");
        GraphFileWriter.writeGraphToFile(instanceGraph, "insttest.in");
    }

    public static void generateGraph() {
        int minVertices = 30;
        int maxVertices = 50;
        int minEdgeWeight = 0;
        int maxEdgeWeight = 100;

        String outputFileNameNoExtension = "BITCHTITS2";

        NPGraph<ColoredVertex,GraphEdge> randomGraph = GraphGenerator
                .generateRandomGraph(minVertices, maxVertices, minEdgeWeight, maxEdgeWeight);
        GraphFileWriter.writeGraphToFile(randomGraph, outputFileNameNoExtension);
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
