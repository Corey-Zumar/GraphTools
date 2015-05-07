package Graph;

import org.jgrapht.generate.CompleteGraphGenerator;

import java.util.*;

/**
 * Created by Corey on 4/28/15.
 */
public class GraphGenerator {

    private static final int MAXIMUM_NUMBER_OF_VERTICES = 50;
    private static final int MAXIMUM_EDGE_WEIGHT = 100;

    public static NPGraph<ColoredVertex, GraphEdge> generateRandomGraph
            (int minVertices, int maxVertices, int minWeight, int maxWeight) {

        NPGraph<ColoredVertex, GraphEdge> randomGraph =
                new NPGraph<ColoredVertex, GraphEdge>(GraphEdge.class);

        Random random = new Random();
        int numVertices = random.ints(minVertices, maxVertices + 1).findFirst().getAsInt();
        if(numVertices % 2 != 0) {
            numVertices++;
        }

        CompleteGraphGenerator completeGraphGenerator = new CompleteGraphGenerator<ColoredVertex, GraphEdge>(numVertices);
        completeGraphGenerator.generateGraph(randomGraph, new ColoredVertexFactory(), null);

        Object[] graphVertices = randomGraph.vertexSet().toArray();

        int index = 0;
        List<Integer> intList = new ArrayList<Integer>();
        while(index < numVertices) {
            intList.add(index++);
        }

        Collections.shuffle(intList, new Random());

        for(int i = 0; i < numVertices / 2; i++) {
            ((ColoredVertex) graphVertices[intList.get(i)]).setColor(ColoredVertex.COLOR_RED);
        }

        for(int i = 0; i < graphVertices.length; i++) {
            ((ColoredVertex) graphVertices[i]).setNumber(i);
        }

        for(GraphEdge edge : randomGraph.edgeSet()) {
            randomGraph.setEdgeWeight(edge, random.ints(minWeight, maxWeight + 1).findFirst().getAsInt());
        }

        return randomGraph;
    }


}
