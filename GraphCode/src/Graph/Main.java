package Graph;

import BranchAndBound.BBSubproblem;
import BranchAndBound.ExactSolver;
import BranchAndBound.OnSolverCompletedListener;
import BranchAndBound.Solution;
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

        /*List<Integer> sizes = InstanceProcessor.getInstanceSizes("../instances/");
        int possibleCount = 0;
        for(Integer size : sizes) {
            if(size <= 18) {
                possibleCount++;
            }
        }
        System.out.println((double) possibleCount / sizes.size());

        int minVertices = 12;
        int maxVertices = 12;
        int minEdgeWeight = 0;
        int maxEdgeWeight = 100;

        NPGraph<ColoredVertex,GraphEdge> randomGraph = GraphGenerator
                .generateRandomGraph(minVertices, maxVertices, minEdgeWeight, maxEdgeWeight); */

        int SIZE_THRESHOLD = 4;

        List<Integer> sizes = InstanceProcessor.getInstanceSizes("../instances/");
        for(int i = 1; i < sizes.size() + 1; i++) {
            if(sizes.get(i - 1) <= SIZE_THRESHOLD) {
                final String fileNamePrefix = String.valueOf(i);
                NPGraph<ColoredVertex,GraphEdge> instanceGraph = null;
                try {
                    instanceGraph = InstanceProcessor.createGraphFromInstance("../instances/" + i + ".in");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(instanceGraph == null) {
                    continue;
                }
                ExactSolver solver = new ExactSolver(instanceGraph, new OnSolverCompletedListener() {
                    @Override
                    public void OnSolverCompleted(BBSubproblem bestSolution) {
                        Solution solution = new Solution(fileNamePrefix + ".in", bestSolution);
                        try {
                            solution.writeToFile();
                            System.out.println("Solved instance " + fileNamePrefix);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                solver.solveExactly();
            }
        }
        System.out.println("DONE");

       // GreedyAlgorithm.greedy(randomGraph);

       /* BBSubproblem solution = BranchAndBoundAlgorithm.branchAndBound(randomGraph);

        System.out.println(solution.currentCost);
        System.out.println(solution.path);

        displayGraph(randomGraph);*/
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
