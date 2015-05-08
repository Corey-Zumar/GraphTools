package Graph;

import BranchAndBound.*;
import GreedyTSP.*;
import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraphView;
import org.jgrapht.ext.JGraphXAdapter;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.List;

/**
 * Created by Corey on 4/28/15.
 */
public class Main {


    public static void main(String[] args) {

        int minVertices = 50;
        int maxVertices = 50;
        int minEdgeWeight = 0;
        int maxEdgeWeight = 100;
        int fileCount = 495;


        NPGraph<ColoredVertex, GraphEdge> randomGraph = GraphGenerator
                .generateRandomGraph(minVertices, maxVertices, minEdgeWeight, maxEdgeWeight);

        for (int i = 2; i < 3; i++) {
            try {
                NPGraph toSolve = processInstanceGraph(i);
                GreedySolver solver = new GreedySolver(toSolve);
                Path sol = solver.solveGreedily();
                List<ColoredVertex> solVertices = sol.vertices;
                GreedySolution solWrite = new GreedySolution(i + ".in", solVertices);
                solWrite.writeToFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


//        runExactSolver();
    }

        //GreedyAlgorithm.greedy(randomGraph);


        //BBSubproblem solution = BranchAndBoundAlgorithm.branchAndBoundRandomStart(randomGraph);
        //System.out.println(solution.path);


        /*try {
            displayGraph(processInstanceGraph(57));
        } catch (IOException e) {
            e.printStackTrace();
        }*/


    public static void runExactSolver() {
        int MIN_SIZE_THRESHOLD = 15;
        int MAX_SIZE_THRESHOLD = 16;

        List<Integer> sizes = InstanceProcessor.getInstanceSizes("../instances/");

        ExactSolverExecutor executor = new ExactSolverExecutor(sizes, MIN_SIZE_THRESHOLD, MAX_SIZE_THRESHOLD);
        try {
            executor.executeRandom();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static NPGraph processInstanceGraph(int number) throws IOException {
        NPGraph instanceGraph = InstanceProcessor.createGraphFromInstance("../instances/" + String.valueOf(number) + ".in");
        return instanceGraph;
    }

    public static NPGraph<ColoredVertex,GraphEdge> generateAndWriteRandomGraph() {
        int minVertices = 30;
        int maxVertices = 50;
        int minEdgeWeight = 0;
        int maxEdgeWeight = 100;

        String outputFileNameNoExtension = "PolynomialLovers1";

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
