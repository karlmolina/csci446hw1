
package csci446hw1;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * CSCI 446 Artificial Intelligence Homework 1 Pacman Search
 *
 * @author Karl Molina, Jordan Palmer
 */
public class Driver {

    /**
     * Runs four search algorithms on three different mazes
     * Breadth First
     * Depth First
     * A Star
     * Greedy Best First
     * 
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {

        //paths to the maze files
        final String mediumMaze = "mazes/medium maze.txt",
                openMaze = "mazes/open maze.txt",
                largeMaze = "mazes/large Maze.txt",
                blankMaze = "mazes/blank Maze.txt";

        //currentMaze for debugging purposes
//        String currentMaze = largeMaze;

        //array of all the mazes to run the algorithms on 
        String[] mazePaths = new String[]{openMaze, mediumMaze, largeMaze};

        //run every algorithm with each maze
        for (String mazePath : mazePaths) {
            Maze maze = new Maze(new File(mazePath), '%', 'P', '*');
            DepthFirst.execute(maze.startNode(), maze);
            BreadthFirst.execute(maze.startNode(), maze);
            GreedyBestFirst.execute(maze.startNode(), maze.goalNode(), maze);
            AStar.execute(maze.startNode(), maze.goalNode(), maze);
        }

        //run mazes individually for debugging purposes
//        Maze maze = new Maze(new File(currentMaze), '%', 'P', '*');
//        GreedyBestFirst.execute(maze.startNode(), maze.goalNode(), maze);
//        AStar.execute(maze.startNode(), maze.goalNode(), maze);
//        BreadthFirst.execute(maze.startNode(), maze);
//        DepthFirst.execute(maze.startNode(), maze);
    }
}
