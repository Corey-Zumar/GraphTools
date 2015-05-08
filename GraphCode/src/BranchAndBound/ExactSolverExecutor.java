package BranchAndBound;

import Graph.ColoredVertex;
import Graph.GraphEdge;
import Graph.InstanceProcessor;
import Graph.NPGraph;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Corey on 5/7/15.
 */
public class ExactSolverExecutor implements OnSolverCompletedListener {

    private int problemsSolved;
    private List<Integer> problems;

    public ExactSolverExecutor(List<Integer> sizes, int maxSize) {
        problems = new ArrayList<Integer>();
        for (int i = 0; i < sizes.size(); i++) {
            if (sizes.get(i) <= maxSize) {
                problems.add(i + 1);
            }
        }
    }

    public ExactSolverExecutor(List<Integer> sizes, int minSize, int maxSize) {
        problems = new ArrayList<Integer>();
        for (int i = 0; i < sizes.size(); i++) {
            if (sizes.get(i) <= maxSize && sizes.get(i) >= minSize) {
                problems.add(i + 1);
            }
        }
    }

    public void executeRandom() throws IOException {
        for(Integer problemNumber : problems) {
            NPGraph<ColoredVertex, GraphEdge> instanceGraph = InstanceProcessor.createGraphFromInstance("../instances/" + problemNumber + ".in");
            ExactSolver solver = new ExactSolver(instanceGraph, String.valueOf(problemNumber), this);
            solver.solveExactlyFromRandomVertex();
        }
    }

    public void execute() throws IOException {
        for(Integer problemNumber : problems) {
            NPGraph<ColoredVertex, GraphEdge> instanceGraph = InstanceProcessor.createGraphFromInstance("../instances/" + problemNumber + ".in");
            ExactSolver solver = new ExactSolver(instanceGraph, String.valueOf(problemNumber), this);
            solver.solveExactly();
        }
    }

    @Override
    public void OnSolverCompleted(BBSubproblem bestSolution, String problemName) {
        problemsSolved++;
        Solution solution = new Solution(problemName + ".in", bestSolution);
        System.out.println("Solved instance " + problemName);
        try {
            solution.writeToFile();
        } catch(IOException e) {
            e.printStackTrace();
        }
        System.out.println("SOLVED: " + problemsSolved + " TOTAL: " + problems.size());
    }
}
