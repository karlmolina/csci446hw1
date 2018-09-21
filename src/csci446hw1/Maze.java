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

    private char[][] array;
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

        array = new char[width][height];
        for (int i = 0; i < height; i++) {
            array[i] = lines.get(i).toCharArray();
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
                System.out.print(array[i][j]);
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
                    System.out.print(array[i][j]);
                }
            }
            System.out.println();
        }
    }

    private char charAt(Point point) {
        return array[point.y()][point.x()];
    }

    void mark(Point point) {
        array[point.y()][point.x()] = '.';
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
                if (array[i][j] == 'P') {
                    return new Node(new Point(j, i), null);
                }
            }
        }
        return null;
    }
}
