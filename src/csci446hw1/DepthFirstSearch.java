/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci446hw1;

import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author Karl
 */
public class DepthFirstSearch {
    public static Node execute(Node root, Maze maze) {
        Stack<Node> frontier = new Stack<>();
        frontier.push(root);
        
        while(!frontier.empty()) {
            Node current = frontier.pop();
            maze.mark(current.point());
            for (Node child: current.availablePaths()) {
                if (!maze.isWall(child.point()) && !(maze.isMarked(child.point()))) {
                    if (maze.isFinish(child.point())) {
                        return child;
                    }
                    frontier.push(child);
                }
            }
            maze.print(current.point());
        }
        return null;
    }
}
