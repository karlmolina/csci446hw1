/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci446hw1;

import java.util.HashSet;
import java.util.LinkedList;

/**
 *
 * @author Karl
 */
public class BreadthFirstSearch {

    public static Node execute(Node root, Maze maze) {
        LinkedList<Node> frontier = new LinkedList<>();
        HashSet<Node> expanded = new HashSet<>();

        frontier.add(root);

        while (!frontier.isEmpty()) {
            Node current = frontier.remove();
            
            if (current.isGoal()) {
                maze.markSolution(current);
                maze.print();
                return current;
            }

            expanded.add(current);

            for (Node child : current.children()) {
                if (!frontier.contains(child) && !expanded.contains(child)) {
                    child.setParent(current);
                    frontier.add(child);
                }
            }

            //maze.markExpanded(expanded, current);
            //maze.print();
        }
        
        return null;
    }
}
