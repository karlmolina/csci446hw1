/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci446hw1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

/**
 *
 * @author Karl
 */
public class DepthFirstSearch {
    public static Node execute(Node root) {
        Stack<Node> frontier = new Stack<>();
        HashSet<Node> expanded = new HashSet<>();
        frontier.push(root);
        Node current = null;
        while(!frontier.empty()) {
            current = frontier.pop();
            expanded.add(current);
            for (Node child: current.children()) {
                //don't know whether to not check frontier, it is better if it does check frontier
                if (!frontier.contains(child) && !expanded.contains(child)) {
                    child.setParent(current);
                    if (child.isFinish()) {
                        return child;
                    }
                    frontier.push(child);
                }
            }
        }
        return current;
    }
}
