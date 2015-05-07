package Graph;

import BranchAndBound.BranchAndBoundAlgorithm;
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
        int minVertices = 6;
        int maxVertices = 6;
        int minEdgeWeight = 0;
        int maxEdgeWeight = 100;

        NPGraph<ColoredVertex,GraphEdge> randomGraph = GraphGenerator
                .generateRandomGraph(minVertices, maxVertices, minEdgeWeight, maxEdgeWeight);

        BranchAndBoundAlgorithm.branchAndBound(randomGraph);
        displayGraph(randomGraph);
    }

    public static void processInstanceGraph() throws IOException {
        NPGraph instanceGraph = InstanceProcessor.createGraphFromInstance("../instances/1.in");
        GraphFileWriter.writeGraphToFile(instanceGraph, "insttest.in");
    }

    public static NPGraph<ColoredVertex,GraphEdge> generateAndWriteRandomGraph() {
        int minVertices = 30;
        int maxVertices = 50;
        int minEdgeWeight = 0;
        int maxEdgeWeight = 100;

        String outputFileNameNoExtension = "BITCHTITS2";

        NPGraph<ColoredVertex,GraphEdge> randomGraph = GraphGenerator
                .generateRandomGraph(minVertices, maxVertices, minEdgeWeight, maxEdgeWeight);
        GraphFileWriter.writeGraphToFile(randomGraph, outputFileNameNoExtension);

        return randomGraph;
    }

    public static void displayGraph(NPGraph graphToDisplay) {
        JGraphXAdapter<ColoredVertex, GraphEdge> adapter = new JGraphXAdapter<ColoredVertex, GraphEdge>(graphToDisplay);

        JFrame frame = new JFrame("NP Graph with " + graphToDisplay.vertexSet().size() + " vertices");
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
