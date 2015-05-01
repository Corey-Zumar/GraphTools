/**
 * Created by Corey on 4/28/15.
 */
public class ColoredVertex {

    public static final int COLOR_BLUE = 0;
    public static final int COLOR_RED = 1;
    public static final int COLOR_DEFAULT = COLOR_BLUE;

    public static final String COLOR_BLUE_READABLE = "B";
    public static final String COLOR_RED_READABLE = "R";

    public int color;
    public int number;

    public ColoredVertex() {
        setColor(COLOR_DEFAULT);
    }

    public ColoredVertex(int color) {
        setColor(color);
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String toString() {
        return String.valueOf(number) + "\n" + (color == COLOR_BLUE ? "BLUE" : "RED");
    }

}
