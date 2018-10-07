
package csci446hw1;

import java.util.HashSet;
import java.util.PriorityQueue;

/**
 *
 * @author Karl Molina, Jordan Palmer
 */
public class GreedyBestFirst {

    /**
     * Executes the Greedy Best First Search algorithm
     * @param start The start Node of the maze
     * @param goal The goal Node
     * @param maze The maze to search through
     * @return 
     */
    public static Node execute(Node start, Node goal, Maze maze) {
        System.out.println("\nGreedy Best First");
        System.out.println(maze.getFileName());
        PriorityQueue<Node> frontier = new PriorityQueue<>();
        HashSet<Node> expanded = new HashSet<>();

        start.setCost(heuristicCostEstimate(start, goal));
        frontier.add(start);
        while (!frontier.isEmpty()) {
            Node current = frontier.remove();
            expanded.add(current);

            //mark current path and print maze for debugging
            //maze.markExpanded(expanded, current);
            //maze.print();
            
            //goal test
            if (current == goal) {
                maze.markSolution(current);
                System.out.println("Number of expanded nodes: " + expanded.size());
                maze.print();
                return current;
            }

            //loop through children to set cost and add children to frontier
            for (Node child : current.children()) {
                if (!expanded.contains(child) && !frontier.contains(child)) {
                    child.setCost(heuristicCostEstimate(child, goal));
                    frontier.add(child);
                    child.setParent(current);
                }
            }
        }

        return null;
    }
    
    /**
     * A heuristic function that computes the Manhattan distance between two 
     * Nodes
     * @param one First Node
     * @param two Second Node
     * @return The distance between the two nodes
     */
    public static int heuristicCostEstimate(Node one, Node two) {
        return Math.abs(one.point().x() - two.point().x()) + Math.abs(one.point().y() - two.point().y());
    }
}
