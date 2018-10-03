/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci446hw1;

import java.util.HashSet;
import java.util.PriorityQueue;

/**
 *
 * @author Jordan
 */
public class Greedy {
    
    public static Node execute(Node start, Node goal, Maze maze) {
        PriorityQueue<Node> frontier = new PriorityQueue<>();
        HashSet<Node> expanded = new HashSet<>();

        start.setCost(heuristicCostEstimate(start, goal));
        frontier.add(start);

        while (!frontier.isEmpty()) {
            Node current = frontier.remove();
            expanded.add(current);
             
            maze.markExpanded(expanded, current);
            maze.print();

            if (current == goal) {
                maze.markSolution(current);
                maze.print();
                return current;
            }
            
            for (Node child : current.children()) {
                if(!expanded.contains(child) && !frontier.contains(child)){
                    child.setCost(heuristicCostEstimate(current, goal));
                    frontier.add(child);
                    child.setParent(current);
                }
            }
        }

        return null;
    }

    public static int heuristicCostEstimate(Node one, Node two) {
        return Math.abs(one.point().x() - two.point().x()) + Math.abs(one.point().y() - two.point().y());
    }
}
 
