import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraphView;
import org.jgraph.JGraph;
import org.jgraph.graph.CellView;
import org.jgraph.graph.GraphCell;
import org.jgrapht.ListenableGraph;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.ext.JGraphXAdapter;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Random;

/**
 * Created by Corey on 4/28/15.
 */
public class Main {


    public static void main(String[] args) {

        ListenableGraph randomGraph = GraphGenerator.generateRandomGraph(4, 8, 0, 100);
        JGraphXAdapter<ColoredVertex, GraphEdge> adapter = new JGraphXAdapter<ColoredVertex, GraphEdge>(randomGraph);

        JFrame frame = new JFrame("Balls");
        frame.setPreferredSize(new Dimension(1000, 1000));

        mxGraphComponent component = new mxGraphComponent(adapter);
        //component.setPreferredSize(new Dimension(2000, 2000));

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
