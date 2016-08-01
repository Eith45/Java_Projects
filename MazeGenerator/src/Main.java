import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Melancholia
 * Date: 10.03.14
 * Time: 21:03
 * To change this template use File | Settings | File Templates.
 */

public class Main {

    public static void main(String[] args) throws IOException {
        // generate a maze with n by m cells
        Maze maze = new Maze(5,5, true);
        maze.display();
        maze = new Maze(5,5, false);
        maze.display();
        maze = new Maze(10,15, false);
        maze.display();
    }

}
