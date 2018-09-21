/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci446hw1;

/**
 *
 * @author Karl
 */
public class Point {
    private final int x, y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int x() {
        return x;
    }
    public int y() {
        return y;
    }
    
    public Point right() {
        return new Point(x + 1, y);
    }

    public Point up() {
        return new Point(x, y - 1);
    }

    public Point left() {
        return new Point(x - 1, y);
    }

    public Point down() {
        return new Point(x, y + 1);
    }
    
    public boolean equals(int x, int y) {
        return this.x == x && this.y == y;
    }
    
    public boolean equals(Point that) {
        return this.x == that.x && this.y == that.y;
    }
}
