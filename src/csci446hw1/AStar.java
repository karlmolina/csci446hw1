
package csci446hw1;

import java.util.HashSet;
import java.util.PriorityQueue;

/**
 *
 * @author Karl Molina, Jordan Palmer
 */
public class AStar {
    
    /**
     * Executes the A Star search algorithm
     * @param start Node to start at
     * @param goal The Node representing the goal
     * @param maze The Maze to conduct the search on
     * @return The finish node
     */
    public static Node execute(Node start, Node goal, Maze maze) {
        System.out.println("\nA Star");
        System.out.println(maze.getFileName());
        PriorityQueue<Node> frontier = new PriorityQueue<>();
        HashSet<Node> expanded = new HashSet<>();

        start.setCostFromStart(0);
        start.setCost(heuristicCostEstimate(start, goal));
        frontier.add(start);

        //loops through the frontier
        while (!frontier.isEmpty()) {
            //expands the current node
            Node current = frontier.remove();
            expanded.add(current);

            //marking the current path and printing the maze for debugging
//            maze.markExpanded(expanded, current);
//            maze.print();
            if (current == goal) {
                maze.markSolution(current);
                System.out.println("Number of expanded nodes: " + expanded.size());
                maze.print();
                return current;
            }

            //loop through children and add/update nodes in frontier
            for (Node child : current.children()) {
                if (expanded.contains(child)) {
                    continue;
                }
                int childCostFromStart = current.getCostFromStart() + 1;

                if (!frontier.contains(child)) {
                    frontier.add(child);
                
                //check if the new cost is worse than the current cost
                } else if (childCostFromStart >= child.getCostFromStart()) {
                    continue;
                }

                //update / add node to the frontier
                child.setParent(current);
                child.setCostFromStart(current.getCostFromStart() + 1);
                child.setCost(child.getCostFromStart() + heuristicCostEstimate(child, goal));
                frontier.remove(child);
                frontier.add(child);
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
