package GreedyTSP;

import Graph.ColoredVertex;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Corey on 5/8/15.
 */
public class Path {

    public List<ColoredVertex> vertices;

    public Path(ColoredVertex startVertex) {
        vertices = new ArrayList<ColoredVertex>();
        vertices.add(startVertex);
    }

    public boolean lastThreeSameColor() {
        int size = vertices.size();

        if(size < 3) {
            return true;
        }

        int lastColor = vertices.get(size - 1).color;
        int secondToLastColor = vertices.get(size - 2).color;
        int thirdToLastColor = vertices.get(size - 3).color;

        return (lastColor == secondToLastColor) && (secondToLastColor == thirdToLastColor);
    }

    public boolean lastTwoSameColor() {
        int size = vertices.size();

        if(size < 2) {
            return true;
        }

        int lastColor = vertices.get(size - 1).color;
        int secondToLastColor = vertices.get(size - 2).color;

        return (lastColor == secondToLastColor);
    }

    public boolean isValid() {
        int sameColor = 1;
        int prevColor = -1;
        for(ColoredVertex vertex : vertices) {
            if(prevColor != vertex.color) {
                sameColor = 0;
                prevColor = vertex.color;
            } else {
                sameColor++;
            }

            if(sameColor >= 4) {
                return false;
            }
        }
        return true;
    }

    public int size() {
        return vertices.size();
    }

    public void addVertex(ColoredVertex vertex) {
        vertices.add(vertex);
    }

    public ColoredVertex getMostRecentVertex() {
        return vertices.get(vertices.size() - 1);
    }

    public String getColorString() {
        StringBuilder colorBuilder = new StringBuilder();
        for(ColoredVertex vertex : vertices) {
            colorBuilder.append(vertex.color == ColoredVertex.COLOR_BLUE ? ColoredVertex.COLOR_BLUE_READABLE :
                    ColoredVertex.COLOR_RED_READABLE);
        }
        return colorBuilder.toString();
    }

    public String toString() {
        return vertices.toString();
    }


}
