
package csci446hw1;

import java.util.HashSet;
import java.util.Stack;

/**
 *
 * @author Karl Molina, Jordan Palmer
 */
public class DepthFirst {

    /**
     * Executes the Depth First Search algorithm
     * @param root The starting Node of the maze
     * @param maze The maze to search through
     * @return 
     */
    public static Node execute(Node root, Maze maze) {
        System.out.println("\nDepth First Search");
        System.out.println(maze.getFileName());
        Stack<Node> frontier = new Stack<>();
        HashSet<Node> expanded = new HashSet<>();
        frontier.push(root);
        Node current = null;

        while (!frontier.empty()) {
            current = frontier.pop();
            expanded.add(current);
            
            //loop through the children and add nodes to frontier
            for (Node child : current.children()) {
                
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
