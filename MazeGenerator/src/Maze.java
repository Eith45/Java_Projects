/**
 * Created with IntelliJ IDEA.
 * User: Melancholia
 * Date: 10.03.14
 * Time: 21:03
 * To change this template use File | Settings | File Templates.
 */

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;


public class Maze{

    private final int x;
    private final int y;
    private boolean debug;
    private final int[][] maze;
    private int [][] tempArray;
    BufferedWriter writer;

    //Constructor
    public Maze(int x, int y, boolean debug) throws IOException {
        this.x = x;
        this.y = y;
        this.debug = debug;
        maze = new int[this.x][this.y];
        tempArray = new int[this.x][this.y];
        generateMaze(0, 0, debug);

    }

    //Displays the maze
    public void display(){
        for (int i = 0; i < y; i++) {
            // draw the north edge
            for (int j = 0; j < x; j++) {
                if((i==0)&&(j==0)){
                    System.out.print("X   ");
                }else{
                    System.out.print((maze[j][i] & 1) == 0 ? "X X " : "X   ");
                }
            }
            System.out.print("X\n");
            // draw the west edge
            for (int j = 0; j < x; j++) {
                if((j==x) && (i == y-1)){
                    System.out.print(" ");
                }                  else{
                    System.out.print((maze[j][i] & 8) == 0 ? "X   " : "    ");}
            }
            if(!(i == y-1)){
                System.out.print("X\n");
            }else{
                System.out.print("\n");
            }
        }
        // draw the bottom line
        for (int j = 0; j < x; j++) {
            System.out.print("X X ");
        }
        System.out.print("X\n" + "\n");
    }

    public void display(int nx, int ny){
        //saving our steps
        tempArray[nx][ny] = maze[nx][ny];
        for (int i = 0; i < y; i++) {
            // draw the north edge
            for (int j = 0; j < x; j++) {
                if((i==0)&&(j==0)){
                    System.out.print("X   ");
                }else{
                    System.out.print((maze[j][i] & 1) == 0 ? "X X " : "X   ");
                }

            }
            System.out.println("X");
            // draw the west edge
            for (int j = 0; j < x; j++) {
                if(i == 0 && j == 0){
                    System.out.print("X V ");
                }else if(tempArray[j][i] != 0){
                    System.out.print((maze[j][i] & 8) == 0 ? "X V " : "  V ");

                }else{
                    System.out.print((maze[j][i] & 8) == 0 ? "X   " : "  V ");
                }

            }
            System.out.println("X");
        }
        // draw the bottom line
        for (int j = 0; j < x - 1; j++) {
            System.out.print("X X ");
        }
        System.out.print("X   X\n" + "\n");
    }


    /*

    Recursive backtracking algorithm

    Here’s the mile-high view of recursive backtracking:
    1) Choose a starting point in the field.
    2)Randomly choose a wall at that point and carve a passage through to the adjacent cell,
      but only if the adjacent cell has not been visited yet. This becomes the new current cell.
    3)If all adjacent cells have been visited, back up to the last cell that has uncarved walls and repeat.
    4)The algorithm ends when the process has backed all the way up to the starting point.

    Recursive backtracker performs a drunkard’s walk from the starting point by randomizing the visited child node at each level,
    but with some modifications on the drunkard’s walk ,as  it pushes each visited node onto a stack
    and when it reached a dead-end it pops the nodes off the stack until it finds one that it can
    continue walking from instead of doing O(n) Search.


      */
    private void generateMaze(int cx, int cy, boolean debug) throws IOException {
        //will show the steps of maze creation if debug is true
        if(this.debug == true){
            display(cx, cy);
        }
        DIR[] dirs = DIR.values();
        Collections.shuffle(Arrays.asList(dirs));
        for (DIR dir : dirs) {
            int nx = cx + dir.dx;
            int ny = cy + dir.dy;
            if (between(nx, x) && between(ny, y)
                    && (maze[nx][ny] == 0)) {
                maze[cx][cy] |= dir.bit;
                maze[nx][ny] |= dir.opposite.bit;
                generateMaze(nx, ny, debug);
            }
        }
    }
    //instance method O(1)
    private static boolean between(int v, int upper) {
        return (v >= 0) && (v < upper);
    }


    private enum DIR {
        N(1, 0, -1), S(2, 0, 1), E(4, 1, 0), W(8, -1, 0);
        private final int bit;
        private final int dx;
        private final int dy;
        private DIR opposite;
        // use the static initializer to resolve forward references
        static {
            N.opposite = S;
            S.opposite = N;
            E.opposite = W;
            W.opposite = E;
        }

        //initialising
        private DIR(int bit, int dx, int dy) {
            this.bit = bit;
            this.dx = dx;
            this.dy = dy;
        }

    };
}
