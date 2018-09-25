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
public class Node implements Comparable<Node>{
    private boolean isGoal;
    private Node parent;
    private Point point;
    private ArrayList<Node> children;
    private int costFromStart = Integer.MAX_VALUE;
    private int costStartToGoal;
    
    public Node(int x, int y) {
        this.parent = null;
        this.children = new ArrayList<>();
        point = new Point(x, y);
    }
    
    public Node(int cost) {
        costStartToGoal = cost;
        point = new Point(0,0);
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
        isGoal = true;
    }
    
    public boolean isGoal() {
        return isGoal;
    }
    
    public ArrayList<Node> children() {
        return children;
    }
    
    public void setCostFromStart(int costFromStart) {
        this.costFromStart = costFromStart;
    }
    
    public int getCostFromStart() {
        return costFromStart;
    }
    
    public void setCostStartToGoal(int costStartToGoal) {
        this.costStartToGoal = costStartToGoal;
    }
    
    public int getCostStartToGoal() {
        return costStartToGoal;
    }
    
    @Override
    public String toString() {
        return point.toString() + " : " + costStartToGoal;
    }

    @Override
    public int compareTo(Node o) {
        return this.costStartToGoal - o.costStartToGoal;
    }
}
