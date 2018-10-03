/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci446hw1;

import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 *
 * @author Karl
 */
public class AStar {

    public static Node execute(Node start, Node goal, Maze maze) {
        PriorityQueue<Node> frontier = new PriorityQueue<>();
        HashSet<Node> expanded = new HashSet<>();

        start.setCostFromStart(0);
        start.setCostStartToGoal(heuristicCostEstimate(start, goal));
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
                if (expanded.contains(child)) {
                    continue;
                }
                int childCostFromStart = current.getCostFromStart() + 1;

                if (!frontier.contains(child)) {
                    frontier.add(child);
                } else if (childCostFromStart >= child.getCostFromStart()) {
                    continue;
                }
                
                child.setParent(current);
                child.setCostFromStart(current.getCostFromStart() + 1);
                child.setCostStartToGoal(child.getCostFromStart() + heuristicCostEstimate(child, goal));
                frontier.remove(child);
                frontier.add(child);
            }
            System.out.println("looped");
        }

        return null;
    }

    public static int heuristicCostEstimate(Node one, Node two) {
        return Math.abs(one.point().x() - two.point().x()) + Math.abs(one.point().y() - two.point().y());
    }
}
