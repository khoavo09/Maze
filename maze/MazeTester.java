package maze;
import java.util.ArrayList;

public class MazeTester {

	public static void main(String[] args) {
		int n = 4;
		Maze maze  = new Maze(n);
		maze.generateSimpleMaze();
		
		//print room
	    maze.printRoom();
	    System.out.println("\n\n");
	
	    //bfs stop
        maze.bfs_stop(maze.cells[0][0],maze.cells[n-1][n-1]);
        maze.print_order();
        System.out.println("\n\n");
        maze.print_path();
		String la = maze.getPath(maze.cells[n-1][n-1]);
		System.out.println(la);
        System.out.println("\n\n");
        
        //dfs stop
        maze.dfs_stop(maze.cells[n-1][n-1]);
        maze.print_order();
        System.out.println("\n\n");
        maze.print_path();
        String la1 = maze.getPath(maze.cells[n-1][n-1]);
		System.out.println(la1);
                

	}

}
