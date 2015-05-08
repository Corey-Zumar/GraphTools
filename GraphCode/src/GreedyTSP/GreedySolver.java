package GreedyTSP;

import Graph.ColoredVertex;
import Graph.GraphEdge;
import Graph.NPGraph;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Corey on 5/8/15.
 */
public class GreedySolver implements OnProblemSolvedListener {

    private NPGraph<ColoredVertex, GraphEdge> graph;

    public GreedySolver(NPGraph<ColoredVertex, GraphEdge> graph) {
        this.graph = graph;
    }

    public void solveGreedily() {
        Greedy2.executeGreedyAlgorithm(graph, 5);
    }

    @Override
    public void OnProblemSolved(Path solution) {

    }
}
