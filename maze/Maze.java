/**
 * Maze.java: Represent a maze which is modeled from graph structure
 */

package maze;
import java.util.ArrayList;
import java.util.Stack;
import java.util.regex.Pattern;

public class Maze extends Graph{
	Cell<Integer>[][] cells; 
	int n; //number of row and col

	//constructor
	public Maze(int n){
		super(n*n); //construct a graph of size n*n
		cells = new Cell[n][n];  
		this.n = n;
		this.generateRooms();
	}
	
	//fill n*n cell with cell numbered from 0->n-1
	private void generateRooms(){
		int name  = 1;
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				Cell a = new Cell(name, i, j); 
				cells[i][j] = a; 	
				this.add(a);		
				name++;				
			}
		}
	}
	
	//generate simple maze
	public void generateSimpleMaze(){
		Stack<Cell> cellStack = new Stack<Cell>();
		int totalCell = n*n;
		Cell currentCell = cells[0][0];
		int visitedCell = 1;
		
		while(visitedCell < totalCell){
			ArrayList<Cell> neighbor = this.findNeighbors(currentCell);
			
			if(neighbor.size()>=1){
				int ran = (int)(Math.random()*(neighbor.size()));
				this.addEgde(new Edge(neighbor.get(ran), currentCell));
				cellStack.push(currentCell);
				currentCell = neighbor.get(ran);
				visitedCell++;
			}else{
				currentCell = cellStack.pop();
			}
		}
	}
	
    // Print the first line of the Maze
    private void printTop(){
        System.out.print("+ ");
        for(int j = 1; j < n;j++)
            System.out.print("+-");
        System.out.println("+");
    }
    
    // Print the last line of the Mazes
    private void printBottom(){
                    
        System.out.print("+");
        for(int j = 1; j < n;j++)
           System.out.print("-+");
        System.out.println(" +");
    }
        
	// Print only the Maze
	public void printRoom(){
        boolean End = false;

        printTop();
        
        for(int i = 0; i < n; i++){
                System.out.print("| ");
                for(int j = 0; j < n-1;j++){
                        if(super.isConnected(cells[i][j], cells[i][j+1]))
                                System.out.print("  ");
                        else
                                System.out.print("| ");
                }
                System.out.println("|");
                        
                for(int j = 0; j < n;j++){
                        if(i+1 == n){
                                End = true;
                                break;
                        }
                        else if(super.isConnected(cells[i][j], cells[i+1][j]))
                                System.out.print("+ ");
                        else
                                System.out.print("+-");
                }
            
                if(End)
                        break;
                else
                        System.out.println("+");
        }
        printBottom();
	}
        
        
	//Print the order that we solve the maze
	public void print_order(){
	    boolean End = false;
	    printTop();         
	    
	    for(int i = 0; i < n; i++){
	            System.out.print("|");
	            for(int j = 0; j < n;j++){
	                    if(i==0 && j==0)
	                    {
	                            if(super.isConnected(cells[i][j], cells[i][j+1]))
	                                    System.out.print("0 ");
	                    else
	                                    System.out.print("0|");
	                    }
	                    else if(j== n-1)
	                    {
	                            if((int)cells[i][j].visitTime == 0)
	                                    System.out.print(" ") ;
	                            else
	                                    System.out.printf("%1s",Math.abs((int)cells[i][j].visitTime % 10)) ;
	                    }
	                    else if(super.isConnected(cells[i][j], cells[i][j+1]))
	                    {
	                            if ((int)cells[i][j].visitTime == 0)
	                                    System.out.print("  ");
	                            else
	                                    System.out.printf("%1s ",Math.abs((int)cells[i][j].visitTime % 10)) ;
	                    }
	                    else
	                    {
	                        if ((int)cells[i][j].visitTime == 0)
	                                System.out.print(" |");
	                        else
	                                System.out.printf("%1s|",Math.abs((int)cells[i][j].visitTime % 10));
	                    }
	            }
	            System.out.println("|");
	                    
	            for(int j = 0; j < n;j++){
	                    if(i+1 == n){
	                            End = true;
	                            break;
	                    }
	                    else if(super.isConnected(cells[i][j], cells[i+1][j]))
	                            System.out.print("+ ");
	                    else
	                            System.out.print("+-");
	            }
	            
	            if(End)
	                    break;
	            else
	                System.out.println("+");
	    }
	    
	    printBottom();
	}
        
        // Print the correct Path from the start to the end room
    public void print_path(){
        boolean End = false;
        boolean found = false;
        int num=1;
        String list = super.getPath(cells[n-1][n-1]);
        printTop();
        for(int i = 0; i < n; i++){
            System.out.print("|");
            for(int j = 0; j < n;j++){
                    String pattern ="\\b" + Integer.toString(num)+"\\b";
                    found = Pattern.compile(pattern).matcher(list).find();
                    if(j== n-1)
                    {
                            if(found)
                                    System.out.print("#") ;
                            else
                                    System.out.print(" ") ;
                
                    }
                    else if (found)
                    {    
                            System.out.print("#") ;
                            if(!super.isConnected(cells[i][j], cells[i][j+1]))
                                    System.out.print ("|") ;
                            else
                                    System.out.print(" ");
                    } 
                    else if(super.isConnected(cells[i][j], cells[i][j+1]))
                            System.out.print ("  ") ;
                    else 
                            System.out.print (" |") ;
            
                    num++;
                    found = false;
            }
            System.out.print("|");
            System.out.println();
                    
            for(int j = 0; j < n;j++){
                    if(i+1 == n){
                            End = true;
                            break;
                    }
                    else if(super.isConnected(cells[i][j], cells[i+1][j]))
                            System.out.print("+ ");
                    else
                            System.out.print("+-");
            }
            
            if(End)
                    break;
            else
                    System.out.println("+");
        }
        
        printBottom();
}
        
        
	//find neighbor of a cell that does not have a path between them yet
	public ArrayList<Cell> findNeighbors(Cell cell){
		//get current cell row and column
		int r = cell.getX();
		int c = cell.getY();
		
		ArrayList<Cell> neighbor = new ArrayList<>();
		
		//get potentail neighbor position
		int[] rowIndex = new int[]{r+1,r-1};
		int[] colIndex = new int[]{c+1,c-1};
		if(r == 0 ){
			rowIndex[0] = r+1;
			rowIndex[1] = -1;
		}else if(r == n-1){
			rowIndex[0] = r-1;
			rowIndex[1] = -1;
		}
			
		if(c == 0){
			colIndex[0] = c+1; 
			colIndex[1] = -1;
		}else if(c == n-1){
			colIndex[0] = c-1;
			colIndex[1] = -1;
		}
		
		//get potential same column neighbor
		for(int i = 0; i < 2; i++){
			Cell cell1 = null;
			if(rowIndex[1]!=-1){
				cell = cells[rowIndex[i]][c];
			}else{
				cell = cells[rowIndex[0]][c];
			}
			if(!this.hasPathBetween(cells[r][c],cell) && !neighbor.contains(cell))
				neighbor.add(cell);		
			
				
		}
		
		//find potential same column neigbor
		for(int i = 0; i < 2; i++){
			Cell cell2 = null;
			if(colIndex[1]!=-1){
				cell2 = cells[r][colIndex[i]];
			}else{
				cell2 = cells[r][colIndex[0]];
			}
			if(!this.hasPathBetween(cells[r][c],cell2) && !neighbor.contains(cell2))
				neighbor.add(cell2);	}
		
		return neighbor;
	}
}
