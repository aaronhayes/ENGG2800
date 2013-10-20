package engg2800.image;

/**
 * Basic coordinate points
 * @author Aaron Hayes
 */
public class Point {
    private int x;
    private int y;

    /**
     * Basic Constructor
     * @param x x coordinate
     * @param y y coordinate
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Get Y Coordinate
     * @return y Coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Get X Coordinate
     * @return x Coordinate
     */
    public int getX() {
        return x;
    }
}
