/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci446hw1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

/**
 *
 * @author Karl
 */
public class BreadthFirstSearch {

    public static Node execute(Node root, Maze maze) {
        LinkedList<Node> frontier = new LinkedList<>();
        frontier.add(root);

        while (!frontier.isEmpty()) {
            Node current = frontier.remove();
            if (maze.isFinish(current.point())) {
                return current;
            }
            
            maze.mark(current.point());
            
            for (Node child : current.availablePaths()) {
                if (!maze.isWall(child.point()) && !(maze.isMarked(child.point()))) {
                    boolean frontierHasChild = false;
                    for (Node n : frontier) {
                        if (n.point().equals(child.point())) {
                            frontierHasChild = true;
                        }
                    }
                    if (!frontierHasChild) {
                        frontier.add(child);
                    }
                }
            }
            //maze.print(current.point());
        }
        return null;
    }
}
