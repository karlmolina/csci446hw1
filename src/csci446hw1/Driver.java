/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci446hw1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * CSCI 446 Artificial Intelligence Homework 1 Pacman Search
 *
 * @author Karl Molina, Jordan Palmer
 */
public class Driver {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        final String mediumMaze = "mazes/medium maze.txt",
                openMaze = "mazes/open maze.txt",
                largeMaze = "mazes/large Maze.txt";

        String currentMaze = openMaze;
        Maze maze = new Maze(new File(currentMaze), '%', 'P', '*');

        Node finish = BreadthFirstSearch.execute(maze.findStart(), maze);

        MazeSolution.printSolution(finish, new Maze(new File(currentMaze)));
    }
}
