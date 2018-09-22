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
    private boolean isFinish;
    
    private Node parent;
    private final Point point;
    private final ArrayList<Node> children;
    
    public Node(int x, int y) {
        this.parent = null;
        this.children = new ArrayList<>();
        point = new Point(x, y);
    }

    public Node parent() {
        return parent;
    }
    
    public void setParent(Node node) {
        this.parent = node;
    }
    
    public Point point() {
        return point;
    }
    
    public void addChild(Node child) {
        children.add(child);
    }
    
    public void makeGoal() {
        isFinish = true;
    }
    
    public boolean isGoal() {
        return isFinish;
    }
    
    public ArrayList<Node> children() {
        return children;
    }
    
    @Override
    public String toString() {
        return point.toString();
    }
}
