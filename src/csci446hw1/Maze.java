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

    private final char[][] mazeCharacters;
    private Node[][] nodes;
    private int height, width;
    private char wall, start, finish;

    public Maze(File mazeFile) throws FileNotFoundException {
        Scanner in = new Scanner(mazeFile);
        ArrayList<String> lines = new ArrayList<>();
        while (in.hasNextLine()) {
            lines.add(in.nextLine());
        }

        width = lines.get(0).length();
        height = lines.size();

        mazeCharacters = new char[width][height];
        for (int i = 0; i < height; i++) {
            mazeCharacters[i] = lines.get(i).toCharArray();
        }

        nodes = new Node[width][height];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (mazeCharacters[i][j] != wall) {
                    nodes[i][j] = new Node(j, i);
                }
                if (mazeCharacters[i][j] == start) {
                    nodes[i][j].makeStart();
                }
                if (mazeCharacters[i][j] == finish) {
                    nodes[i][j].makeFinish();
                }
            }
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Node current = nodes[i][j];
                if (null != null) {
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

    public Maze(File mazeFile, char wall, char start, char finish) throws FileNotFoundException {
        this(mazeFile);

        print();

        this.wall = wall;
        this.start = start;
        this.finish = finish;
    }

    void print() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(mazeCharacters[i][j]);
            }
            System.out.println();
        }
    }

    void print(Point point) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (point.equals(j, i)) {
                    System.out.print("X");
                } else {
                    System.out.print(mazeCharacters[i][j]);
                }
            }
            System.out.println();
        }
    }

    private char charAt(Point point) {
        return mazeCharacters[point.y()][point.x()];
    }

    void mark(Point point) {
        mazeCharacters[point.y()][point.x()] = '.';
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
                if (mazeCharacters[i][j] == 'P') {
                    return new Node(new Point(j, i), null);
                }
            }
        }
        return null;
    }
}
