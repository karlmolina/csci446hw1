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
public class DepthFirstSearch {
    public static Node execute(Node root, Maze maze) {
        Stack<Node> frontier = new Stack<>();
        HashSet<Node> expanded = new HashSet<>();
        frontier.push(root);
        Node current = null;
        int count = 0;
        while(!frontier.empty()) {
            count++;
            current = frontier.pop();
            expanded.add(current);
            for (Node child: current.children()) {
                //if you don't check the frontier then you expand nodes twice
                if (!frontier.contains(child) && !expanded.contains(child)) {
                    child.setParent(current);
                    if (child.isGoal()) {
                        return child;
                    }
                    frontier.push(child);
                }
            }
            maze.markExpanded(expanded, current);
            maze.print();
        }
        System.out.println(count);
        return current;
    }
}
