/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci446hw1;

import java.io.File;

/**
 *
 * @author Karl
 */
public class MazeSolution {
    public static void printSolution(Node finish, Maze maze) {
        Node current = finish.parent();

        while (current.parent() != null) {
            maze.mark(current.point());
            current = current.parent();
        }

        maze.print();
    }
}
