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
 *
 * @author Karl
 */
public class Maze {

    private final char[][] characters;
    private Node[][] nodes;
    private int height, width;
    private char wallCharacter, startCharacter, finishCharacter;
    private Node startNode, finishNode;

    public Maze(File mazeFile) throws FileNotFoundException {
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

    public Maze(File mazeFile, char wall, char start, char finish) throws FileNotFoundException {
        this(mazeFile);

        print();

        this.wallCharacter = wall;
        this.startCharacter = start;
        this.finishCharacter = finish;
        
        

        nodes = new Node[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                char currentCharacter = characters[i][j];
                Node currentNode = null;
                if (currentCharacter != wallCharacter) {
                    currentNode = new Node(j, i);
                }
                if (currentCharacter == startCharacter) {
                    currentNode.makeStart();
                    startNode = currentNode;
                }
                if (currentCharacter == finishCharacter) {
                    currentNode.makeFinish();
                }
                nodes[i][j] = currentNode;
            }
        }

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

    void print() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(characters[i][j]);
            }
            System.out.println();
        }
    }
    
    Node startNode() {
        return startNode;
    }

    void print(Point point) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (point.equals(j, i)) {
                    System.out.print("X");
                } else {
                    System.out.print(characters[i][j]);
                }
            }
            System.out.println();
        }
    }

    private char charAt(Point point) {
        return characters[point.y()][point.x()];
    }

    void mark(Point point) {
        characters[point.y()][point.x()] = '.';
    }

    boolean isMarked(Point point) {
        return charAt(point) == '.';
    }

    boolean isWall(Point point) {
        return charAt(point) == '%';
    }

    boolean isFinish(Point point) {
        return charAt(point) == '*';
    }

    Node findStart() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (characters[i][j] == 'P') {
                    return new Node(new Point(j, i), null);
                }
            }
        }
        return null;
    }
}
