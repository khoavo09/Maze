package maze;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MazeJUnitTest {
	@Test public void solveMaze(){
		long bfsStart = 0;
		long bfsEnd = 0;
		long dfsStart = 0;
		long dfsEnd = 0;
		
		for(int n = 4; n <= 10; n++){
			System.out.println("Solving maze size "+n);
			Maze maze = new Maze(n);
			maze.generateSimpleMaze();
			
			//print empty maze
		    maze.printRoom();
		    System.out.println("\n");
		    
		    //use bfs_stop to search and print order
		    bfsStart = System.currentTimeMillis();
		    maze.bfs_stop(maze.cells[0][0],maze.cells[n-1][n-1]);
		    bfsEnd = System.currentTimeMillis();
		    
		    System.out.println("Solve time for bfs: "+ (bfsEnd - bfsStart)+" miliseconds");
	        maze.print_order();
	        System.out.println("\n");
	        
	        maze.print_path();
	        System.out.println("\n");
	        
	        //use dfs_stop to search and print order
	        dfsStart = System.currentTimeMillis();
	        maze.dfs_stop(maze.cells[n-1][n-1]);
	        dfsEnd = System.currentTimeMillis();
	        
	        System.out.println("Solve time for dfs: "+ (dfsEnd - dfsStart)+ " miliseconds");
	        maze.print_order();
	        System.out.println("\n");
	        
	        maze.print_path();
	        System.out.println("\n");
		}
	}
}
