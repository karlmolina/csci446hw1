
package csci446hw1;

import java.util.HashSet;
import java.util.LinkedList;

/**
 *
 * @author Karl Molina, Jordan Palmer
 */
public class BreadthFirst {

    /**
     * Executes the breadth first search algorithm
     * @param root The start of the maze
     * @param maze The maze to search through
     * @return The finish node set with it's parents of the path it took
     */
    public static Node execute(Node root, Maze maze) {
        System.out.println("\nBreadth First Search");
        System.out.println(maze.getFileName());
        LinkedList<Node> frontier = new LinkedList<>();
        HashSet<Node> expanded = new HashSet<>();

        frontier.add(root);

        while (!frontier.isEmpty()) {
            Node current = frontier.remove();

            if (current.isGoal()) {
                maze.markSolution(current);
                System.out.println("Number of expanded nodes: " + expanded.size());
                maze.print();
                return current;
            }

            expanded.add(current);

            //loop through children and add nodes to frontier
            for (Node child : current.children()) {
                if (!frontier.contains(child) && !expanded.contains(child)) {
                    child.setParent(current);
                    frontier.add(child);
                }
            }

            //maze.markExpanded(expanded, current);
            //maze.print();
        }

        return null;
    }
}
