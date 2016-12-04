package maze;
import java.util.ArrayList;

//Vertex class
//For example usage, refer GraphTester

//enum COLOR
enum COLOR{
	WHITE, GREY, BLACK;
}

public class Vertex<T> {
	double d; //timestamp d
	int f; // timestamp f
	COLOR color; //color enum
	Vertex parent; //parent
	T label; //label 
	int index; // index for easy access when refering to adjacency list (to get to the adjacency list of "e" just do vertexList[e.index]
	boolean theEnd;
    int visitTime;
	
	//this variable is currently not in used, delete if you end up using it
	ArrayList<Vertex> neighbor;
	
	//constructor
	public Vertex(T label){
		this.label = label;
		parent = null;
		color = COLOR.WHITE;
		d = Double.POSITIVE_INFINITY; 
                boolean theEnd = false;
		
		theEnd = false;
	}
	
	public String toString(){
		return "["+label+"]";
	}
        
	
	//not used
	public void addEdge(Vertex a){
            
		neighbor.add(a);
                System.out.println("SIZE"+neighbor.size());
	}
	
	public void setIndex(int a){
		index = a;
	}
	
	public int getIndex(){
		return index;
	}
}
