package Graph;

import java.io.*;

/**
 * Created by Corey on 5/5/15.
 */
public class GraphFileWriter {

    public static void writeGraphToFile(NPGraph graph, String fileName) {
        try {
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName + ".in"), "utf-8"));
            writer.write(graph.toString());
            System.out.println(graph.toString());
            writer.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
