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
public class GreedyBestFirst {

    public static Node execute(Node start, Node goal, Maze maze) {
        System.out.println("Greedy Best First");
        System.out.println(maze.getFileName());
        PriorityQueue<Node> frontier = new PriorityQueue<>();
        HashSet<Node> expanded = new HashSet<>();

        start.setCost(heuristicCostEstimate(start, goal));
        frontier.add(start);
        //int count = 0;
        while (!frontier.isEmpty()) {
            Node current = frontier.remove();
            expanded.add(current);

            //System.out.println(count);
            //maze.markExpanded(expanded, current);
            //maze.print();
            if (current == goal) {
                maze.markSolution(current);
                System.out.println("Number of expanded nodes: " + expanded.size());
                maze.print();
                return current;
            }

            for (Node child : current.children()) {
                if (!expanded.contains(child) && !frontier.contains(child)) {
                    child.setCost(heuristicCostEstimate(child, goal));
                    frontier.add(child);
                    child.setParent(current);
                }
            }
            //count++;
        }

        return null;
    }

    public static int heuristicCostEstimate(Node one, Node two) {
        return Math.abs(one.point().x() - two.point().x()) + Math.abs(one.point().y() - two.point().y());
    }
}
