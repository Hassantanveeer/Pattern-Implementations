package model;

/**
 *     This is class for storing coordinate, other class are able to derive it through getter
 *     and use it for creating shape
 *     Sprint 2: Setter implemented for observer pattern, it will update the new coordinate once shape get notified
 */
public class Point {

    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}