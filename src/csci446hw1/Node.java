/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci446hw1;

import java.util.ArrayList;

/**
 *
 * @author Karl
 */
public class Node {
    private boolean isStart = false;
    private boolean isFinish = false;
    
    private Node parent = null;
    private final Point point;
    private ArrayList<Node> children = new ArrayList<>();
    
    public Node(int x, int y) {
        point = new Point(x, y);
    }
    
    public Node(Point point, Node parent) {
        this.point = point;
        this.parent = parent;
    }
    
    public Node[] availablePaths() {
        return new Node[]{
            new Node(point.right(), this),
            new Node(point.up(), this),
            new Node(point.left(), this),
            new Node(point.down(), this)
        };
    }

    public Node parent() {
        return parent;
    }
    
    public Point point() {
        return point;
    }
    
    public void addChild(Node child) {
        children.add(child);
    }
    
    public void makeStart() {
        isStart = true;
    }
    
    public void makeFinish() {
        isFinish = true;
    }
    
    public boolean isStart() {
        return isStart;
    }
    
    public boolean isFinish() {
        return isFinish;
    }
}
