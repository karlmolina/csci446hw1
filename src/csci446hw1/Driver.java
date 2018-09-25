/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci446hw1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;

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
        
        System.out.println(AStar.heuristicCostEstimate(new Node(40, 1), new Node(30, 4)));
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(4));
        pq.add(new Node(2));
        pq.add(new Node(3));
        pq.add(new Node(1));
        
        while(!pq.isEmpty()) {
            System.out.println(pq.remove());
        }
        
        final String mediumMaze = "mazes/medium maze.txt",
                openMaze = "mazes/open maze.txt",
                largeMaze = "mazes/large Maze.txt",
                blankMaze = "mazes/blank Maze.txt";

        String currentMaze = largeMaze;

        String[] mazePaths = new String[] { openMaze, mediumMaze, largeMaze };
        
        for (String mazePath : mazePaths) {
            
        //DepthFirstSearch.execute(maze.startNode(), maze);
        //BreadthFirstSearch.execute(maze.startNode(), maze);
        
        }
        Maze maze = new Maze(new File(currentMaze), '%', 'P', '*');
        AStar.execute(maze.startNode(), maze.goalNode(), maze);
    }
}
