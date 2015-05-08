package BranchAndBound;

import Graph.ColoredVertex;
import Graph.GraphEdge;
import Graph.NPGraph;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Corey on 5/6/15.
 */
public class ExactSolver implements OnProblemSolvedListener {

    private NPGraph<ColoredVertex,GraphEdge> graph;
    private int verticesSolvedFrom;
    private BBSubproblem bestSolution;
    private OnSolverCompletedListener listener;

    public ExactSolver(NPGraph<ColoredVertex,GraphEdge> inputGraph, OnSolverCompletedListener listener) {
        this.graph = inputGraph;
        this.listener = listener;
    }

    public void solveExactly() {
        graph.getVerticesAsList(); //Make sure the vertices list is ready before we spawn new threads
        ExecutorService executorService = Executors.newFixedThreadPool(graph.vertexSet().size());
        for(final ColoredVertex vertex : graph.vertexSet()) {
            executorService.execute(new Runnable() {

                @Override
                public void run() {
                    OnProblemSolved(BranchAndBoundAlgorithm.branchAndBound(graph, vertex.number));
                }
            });
        }
    }


    @Override
    public void OnProblemSolved(BBSubproblem solution) {
        if(bestSolution == null || solution.compareTo(bestSolution) < 0) {
            bestSolution = solution;
        }
        verticesSolvedFrom++;

        if(verticesSolvedFrom == graph.vertexSet().size()) {
            listener.OnSolverCompleted(bestSolution);
        }
    }
}
