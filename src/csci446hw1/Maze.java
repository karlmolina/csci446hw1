/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci446hw1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/**
 *
 * @author Karl
 */
public class Maze {

    private char[][] characters;
    private final char[][] cleanCharacters;
    private final Node[][] nodes;
    private int height, width;
    private final char wallCharacter, startCharacter, goalCharacter;
    private Node startNode, goalNode;
    private final File mazeFile;

    public Maze(File mazeFile, char wall, char start, char goal) throws FileNotFoundException {
        this.mazeFile = mazeFile;
        processFile();

        this.wallCharacter = wall;
        this.startCharacter = start;
        this.goalCharacter = goal;

        nodes = new Node[height][width];
        cleanCharacters = new char[height][width];
        
        //populate the 2d Node array
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                char currentCharacter = characters[i][j];
                cleanCharacters[i][j] = currentCharacter;
                Node currentNode = new Node(j, i);

                if (currentCharacter == startCharacter) {
                    startNode = currentNode;
                } else if (currentCharacter == goalCharacter) {
                    currentNode.makeGoal();
                    goalNode = currentNode;
                } else if (currentCharacter == wallCharacter) {
                    currentNode = null;
                }
                nodes[i][j] = currentNode;
            }
        }
        
        //Add children to all the nodes to connect them
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Node current = nodes[i][j];
                if (current != null) {
                    for (Point p : current.point().surrounding()) {
                        Node possibleChild = nodes[p.y()][p.x()];
                        if (possibleChild != null) {
                            current.addChild(possibleChild);
                        }
                    }
                }
            }
        }
    }

    private void processFile() throws FileNotFoundException {
        Scanner in = new Scanner(mazeFile);
        ArrayList<String> lines = new ArrayList<>();
        while (in.hasNextLine()) {
            lines.add(in.nextLine());
        }

        width = lines.get(0).length();
        height = lines.size();

        characters = new char[height][width];
        for (int i = 0; i < height; i++) {
            characters[i] = lines.get(i).toCharArray();
        }
    }

    public final void print() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(characters[i][j]);
            }
            System.out.println();
        }
    }

    public Node startNode() {
        return startNode;
    }
    
    public Node goalNode() {
        return goalNode;
    }

    private void mark(Point point) {
        mark(point, '.');
    }

    private void mark(Point point, char c) {
        characters[point.y()][point.x()] = c;
    }

    public void clean() {
        for (int i = 0; i < height; i++) {
            System.arraycopy(cleanCharacters[i], 0, characters[i], 0, width);
        }
    }

    public void markSolution(Node finish) {
        clean();
        Node current = finish.parent();
        int count = 1;
        while (current.parent() != null) {
            mark(current.point());
            current = current.parent();
            count++;
        }
        System.out.println("Solution Cost: " + count);
    }

    public void markPath(Node last) {
        Node current = last.parent();
        if (current != null) {

            while (current.parent() != null) {
                mark(current.point(), 'o');
                current = current.parent();
            }

            if (!last.isGoal()) {
                mark(last.point(), 'X');
            }
        }
    }

    public void markExpanded(HashSet<Node> expanded, Node last) {
        clean();
        expanded.forEach((node) -> {
            mark(node.point());
        });
        mark(last.point(), 'X');
        markPath(last);
    }
    
    public String getFileName() {
        return mazeFile.getName();
    }
}
