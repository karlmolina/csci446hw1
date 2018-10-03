/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci446hw1;

import java.util.HashSet;
import java.util.Stack;

/**
 *
 * @author Karl
 */
public class DepthFirst {
    public static Node execute(Node root, Maze maze) {
        System.out.println("Depth First Search");
        Stack<Node> frontier = new Stack<>();
        HashSet<Node> expanded = new HashSet<>();
        frontier.push(root);
        Node current = null;
        
        while(!frontier.empty()) {
            current = frontier.pop();
            expanded.add(current);
            for (Node child: current.children()) {
                //if you don't check the frontier then you expand nodes twice
                if (!frontier.contains(child) && !expanded.contains(child)) {
                    child.setParent(current);
                    if (child.isGoal()) {
                        maze.markSolution(child);
                        System.out.println("Number of expanded nodes: " + expanded.size());
                        maze.print();
                        return child;
                    }
                    frontier.push(child);
                }
            }
            //maze.markExpanded(expanded, current);
            //maze.print();
        }
        return current;
    }
}
