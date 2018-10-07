
package csci446hw1;

import java.util.ArrayList;

/**
 * Node class to use for the search algorithms
 * Holds parent and children to navigate through a graph or tree
 * @author Karl Molina, Jordan Palmer
 */
public class Node implements Comparable<Node>{
    private boolean isGoal;
    private Node parent;
    private Point point;
    private ArrayList<Node> children;
    private int costFromStart = Integer.MAX_VALUE;
    private int cost;

    
    public Node(int x, int y) {
        this.parent = null;
        this.children = new ArrayList<>();
        point = new Point(x, y);
    }
    
    public Node(int cost) {
        this.cost = cost;
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
    
    public void setCost(int cost) {
        this.cost = cost;
    }
    
    public int getCost() {
        return cost;
    }
 
    
    @Override
    public String toString() {
        return point.toString() + " : " + cost;
    }

    @Override
    public int compareTo(Node o) {
        return this.cost - o.cost;
    }
}
